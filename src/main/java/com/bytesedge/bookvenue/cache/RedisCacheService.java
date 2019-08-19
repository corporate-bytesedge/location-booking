package com.bytesedge.bookvenue.cache;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bytesedge.bookvenue.core.ServerConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class RedisCacheService {
	private static Logger logger = Logger.getLogger(RedisCacheService.class);
	private static JedisPool jedisPool = null;
	private static final String poolType = "cache";

	private RedisCacheService() {
	}

	public static void init() throws Exception {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(ServerConfig.getIntegerProperty(ServerConfig.REDIS_CONN_PREFIX + poolType, 1000));
		config.setMaxIdle(config.getMaxTotal() / 2);
		config.setTestOnBorrow(true);
		config.setMaxWaitMillis(30000);

		jedisPool = new JedisPool(config,
				ServerConfig.getStringProperty(ServerConfig.REDIS_IP_ADDR + poolType, "127.0.0.1"),
						ServerConfig.getIntegerProperty(ServerConfig.REDIS_PORT + poolType, 6379));
		logger.error("MSG : Successfully connected to RedisCacheService");
	}

	@Override
	protected void finalize() throws Throwable {
		if (jedisPool != null) {
			try {
				jedisPool.destroy();
			} catch (Exception e) {
				logger.error("Failed to destroy redispool");
			}
		}
	}

	public static void pSubscribe(JedisPubSub jedisPubSub, String patterns) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.psubscribe(jedisPubSub, patterns);
		} catch (Exception e) {
			logger.error("Failed to psubscribe into redis for " + jedisPubSub + "/" + patterns + " : " + e.getMessage(),
					e);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public static String get(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error getting from redis " + e.getMessage(), e);
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return null;
	}

	public static void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error setting into redis " + e.getMessage(), e);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public static void setWithExpiry(String key, String value, int timeToLiveInSeconds) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.setex(key, timeToLiveInSeconds, value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error setting into redis " + e.getMessage(), e);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public static String hGet(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hget(key, field);
		} catch (Exception e) {
			logger.error("Error getting hash from redis: " + key + "/" + field + " : " + e.getMessage(), e);
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return null;
	}

	public static long volatileHCounter(String key, String field, long expireAt) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Long count = jedis.hincrBy(key, field, 1);
			if (count == null) {
				count = 0L;
			}
			if (count <= 5) {
				Long expStatus = jedis.expireAt(key, expireAt);
				if (expStatus != 1) {
					logger.error(
							"Unable to set expiry for key: " + key + "; status = " + expStatus + "; count = " + count);
				}
			}
			return count;
		} catch (Exception e) {
			logger.error("Error incrementing in redis: " + key + "/" + field + " : " + e.getMessage(), e);
			return 0L;
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public static void setExpireAt(String key, long expireAt) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Long expStatus = jedis.expireAt(key, expireAt);
			if (expStatus != 1) {
				logger.error("Unable to set expiry for key: " + key + "; status = " + expStatus + "; expireAt = "
						+ expireAt);
			}
		} catch (Exception e) {
			logger.error("Error setting into redis " + key + "/" + expireAt + " : " + e.getMessage(), e);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public static long setCounterKey(String key, int incrementBy) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Long count = jedis.incrBy(key, incrementBy);
			if (count == null) {
				count = 0L;
			}
			return count;
		} catch (Exception e) {
			logger.error("Error setting into redis " + key + "/" + incrementBy + " : " + e.getMessage(), e);
			return 0L;
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public static void setList(String key, List<String> valueList) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (valueList != null && valueList.size() > 0) {
				jedis.del(key);
				for (String val : valueList) {
					jedis.lpush(key, val);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to set list" + e.getMessage(), e);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public static List<String> getList(String key, int sIdx, int eIdx) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if ((sIdx >= 0) && (eIdx >= 0) && (sIdx <= eIdx)) {
				return jedis.lrange(key, sIdx, eIdx);
			}
		} catch (Exception e) {
			logger.error("Failed to get list" + e.getMessage(), e);
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return null;
	}

	public static void setMap(String key, Map<String, String> valueMap) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if ((valueMap != null) && (valueMap.size() > 0)) {
				for (String valKey : valueMap.keySet()) {
					jedis.hset(key, valKey, valueMap.get(valKey));
				}
				;
			}

		} catch (Exception e) {
			logger.error("Failed to set map" + e.getMessage(), e);
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public static void setMap(String redisKey, String mapKey, String mapValue) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hset(redisKey, mapKey, mapValue);
		} catch (Exception e) {
			logger.error("Failed to set map" + e.getMessage(), e);
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public static void deleteMap(String key, String mapKey) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hdel(key, mapKey);

		} catch (Exception e) {
			logger.error("Failed to set map" + e.getMessage(), e);
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public static Map<String, String> getMap(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			logger.error("Failed to get map" + e.getMessage(), e);
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return null;
	}

	public static String getMapValue(String key, String valKey) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hget(key, valKey);
		} catch (Exception e) {
			logger.error("Failed to get map value" + e.getMessage(), e);
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return null;
	}

	public static long getListSize(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.llen(key);
		} catch (Exception e) {
			logger.error("Failed to get size" + e.getMessage(), e);
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return 0;
	}

	public static void removeKey(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			logger.error("Failed to delete key" + e.getMessage(), e);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

}