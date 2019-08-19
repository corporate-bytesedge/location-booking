
package com.bytesedge.bookvenue.controller.paymentGatewayDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.common.DbService;
import com.bytesedge.bookvenue.common.EmailService;
import com.bytesedge.bookvenue.common.SmsServiceFactory;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.PaymentGatewayDetails;
import com.bytesedge.bookvenue.model.PaymentStatus;
import com.bytesedge.bookvenue.pg.PaymentGatewaySignatureGenerateAtom;

@Controller
@SessionAttributes({ "endUserForm" })
public class PaymentGatewayResponseController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentGatewayResponseController.class);
	private final String smsTemplate = "SMS - Your venue booking process is successfully done.";

	@RequestMapping(value = "/apu/ext/paymentGatewayResponse", method = { RequestMethod.POST}, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ModelAndView paymentGatewayDetailsList(HttpServletRequest request,
			HttpServletResponse response, @RequestBody MultiValueMap<String, String> formParams) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			System.out.println("paymentGatewayResponse");
			for (String key : formParams.keySet()) {
				System.out.println(key + "=" + formParams.get(key));
			}
			String mmp_txn = null;
			List<String> mmp_txn_list = formParams.get("mmp_txn");
			if (mmp_txn_list != null && mmp_txn_list.size() > 0) {
				mmp_txn = mmp_txn_list.get(0);
				
			}
			System.out.println("mmp_txn" + mmp_txn);

			String mer_txn = null;
			List<String> mer_txn_list = formParams.get("mer_txn");
			if (mer_txn_list != null && mer_txn_list.size() > 0) {
				mer_txn = mer_txn_list.get(0);
			}
			System.out.println("mer_txn" + mer_txn);

			String f_code = null;
			List<String> f_code_list = formParams.get("f_code");
			if (f_code_list != null && f_code_list.size() > 0) {
				f_code = f_code_list.get(0);
			}
			System.out.println("f_code" + f_code);

			String prod = null;
			List<String> prod_list = formParams.get("prod");
			if (prod_list != null && prod_list.size() > 0) {
				prod = prod_list.get(0);
			}
			System.out.println("prod" + prod);

			String bank_name = null;
			List<String> bank_name_list = formParams.get("bank_name");
			if (bank_name_list != null && bank_name_list.size() > 0) {
				bank_name = bank_name_list.get(0);
			}
			System.out.println("bank_name" + bank_name);

			String auth_code = null;
			List<String> auth_code_list = formParams.get("auth_code");
			if (auth_code_list != null && auth_code_list.size() > 0) {
				auth_code = auth_code_list.get(0);
			}
			System.out.println("auth_code" + auth_code);

			String ipg_txn_id = null;
			List<String> ipg_txn_id_list = formParams.get("ipg_txn_id");
			if (ipg_txn_id_list != null && ipg_txn_id_list.size() > 0) {
				ipg_txn_id = ipg_txn_id_list.get(0);
			}
			System.out.println("ipg_txn_id" + ipg_txn_id);

			String merchant_id = null;
			List<String> merchant_id_list = formParams.get("merchant_id");
			if (merchant_id_list != null && merchant_id_list.size() > 0) {
				merchant_id = merchant_id_list.get(0);
			}
			System.out.println("merchant_id" + merchant_id);

			String desc = null;
			List<String> desc_list = formParams.get("desc");
			if (desc_list != null && desc_list.size() > 0) {
				desc = desc_list.get(0);
			}
			System.out.println("desc" + desc);

			String udf9 = null;
			List<String> udf9_list = formParams.get("udf9");
			if (udf9_list != null && udf9_list.size() > 0) {
				udf9 = udf9_list.get(0);
			}
			System.out.println("udf9" + udf9);

			String udf5 = null;
			List<String> udf5_list = formParams.get("udf5");
			if (udf5_list != null && udf5_list.size() > 0) {
				udf5 = udf5_list.get(0);
			}
			System.out.println("udf5" + udf5);

			String udf6 = null;
			List<String> udf6_list = formParams.get("udf6");
			if (udf6_list != null && udf6_list.size() > 0) {
				udf6 = udf6_list.get(0);
			}
			System.out.println("udf6" + udf6);

			String udf4 = null;
			List<String> udf4_list = formParams.get("udf4");
			if (udf4_list != null && udf4_list.size() > 0) {
				udf4 = udf4_list.get(0);
			}
			System.out.println("udf4" + udf4);

			String udf3 = null;
			List<String> udf3_list = formParams.get("udf3");
			if (udf3_list != null && udf3_list.size() > 0) {
				udf3 = udf3_list.get(0);
			}
			System.out.println("udf3" + udf3);

			String udf2 = null;
			List<String> udf2_list = formParams.get("udf2");
			if (udf2_list != null && udf2_list.size() > 0) {
				udf2 = udf2_list.get(0);
			}
			System.out.println("udf2" + udf2);

			String udf1 = null;
			List<String> udf1_list = formParams.get("udf1");
			if (udf1_list != null && udf1_list.size() > 0) {
				udf1 = udf1_list.get(0);
			}
			System.out.println("udf1" + udf1);

			String discriminator = null;
			List<String> discriminator_list = formParams.get("discriminator");
			if (discriminator_list != null && discriminator_list.size() > 0) {
				discriminator = discriminator_list.get(0);
			}
			System.out.println("discriminator" + discriminator);

			String amt = null;
			List<String> amt_list = formParams.get("amt");
			if (amt_list != null && amt_list.size() > 0) {
				amt = amt_list.get(0);
			}
			System.out.println("amt" + amt);

			String bank_txn = null;
			List<String> bank_txn_list = formParams.get("bank_txn");
			if (bank_txn_list != null && bank_txn_list.size() > 0) {
				bank_txn = bank_txn_list.get(0);
			}
			System.out.println("bank_txn" + bank_txn);

			String CardNumber = null;
			List<String> CardNumber_list = formParams.get("CardNumber");
			if (CardNumber_list != null && CardNumber_list.size() > 0) {
				CardNumber = CardNumber_list.get(0);
			}
			System.out.println("CardNumber" + CardNumber);

			String date = null;
			List<String> date_list = formParams.get("date");
			if (date_list != null && date_list.size() > 0) {
				date = date_list.get(0);
			}
			System.out.println("date" + date);

			String clientcode = null;
			List<String> clientcode_list = formParams.get("clientcode");
			if (clientcode_list != null && clientcode_list.size() > 0) {
				clientcode = clientcode_list.get(0);
			}
			System.out.println("clientcode" + clientcode);

			String surcharge = null;
			List<String> surcharge_list = formParams.get("surcharge");
			if (surcharge_list != null && surcharge_list.size() > 0) {
				surcharge = surcharge_list.get(0);
			}
			System.out.println("surcharge" + surcharge);

			String resSignature = null;
			List<String> resSignature_list = formParams.get("signature");
			if (resSignature_list != null && resSignature_list.size() > 0) {
				resSignature = resSignature_list.get(0);
			}
			System.out.println("resSignature" + resSignature);

			System.out.println("clientcode:" + clientcode);
			System.out.println("resSignature:" + resSignature);
			EndUser enduser = DbService.getInstance().getSetupService().getEndUserByUsername(clientcode);
			if (enduser != null) {
				PaymentGatewayDetails pgd = DbService.getInstance().getSetupService()
						.getPaymentGatewayDetailsByCtxIdAndOrgId(ControllerUtil.getContextId(request),
								enduser.getOrgId());
				enduser.setPaymentUrl(pgd.getMerchantUrl());
				if (pgd != null) {
					String resSig = PaymentGatewaySignatureGenerateAtom.getPaymentGatewayResSig(pgd.getResHashKey(),
							mmp_txn, mer_txn, f_code, prod, discriminator, amt, bank_txn);
					System.out.println("1=" + resSig);
					System.out.println("2=" + resSignature);

					if (resSignature != null && resSig != null && resSig.equals(resSignature)) {
						if (f_code.equalsIgnoreCase("OK")) {
							// Sucess
							EndUser endUser = DbService.getInstance().getSetupService()
									.getEndUserByCtxIdAndOrgIdAndUserName(ControllerUtil.getContextId(request),
											enduser.getOrgId(), enduser.getUserName());
							if (endUser != null) {
								endUser.setPaymentStatus(PaymentStatus.PAID);
								endUser.setPropertyName(CacheService
										.getPropertyById(ControllerUtil.getContextId(request), endUser.getPropertyId())
										.getName());
								endUser.setPurposeName(CacheService
										.getPurposeById(ControllerUtil.getContextId(request), endUser.getPurposeId()).getName()
										);
								DbService.getInstance().getSetupService().saveOrUpdateEndUser(endUser);
								System.out.println("3=success");
								// email sent
								Map<String, Object> attr = new HashMap<String, Object>();
								attr.put("bill", endUser);
								attr.put("server_scheme", request.getScheme());
								attr.put("server_hostname", request.getServerName());
								attr.put("server_port", request.getServerPort());
								EmailService.getInstance().sendEmail(endUser.getEmail(), "endUser/bill.vm",
										"Bill with name " + endUser.getName() + " has been created", attr);
								SmsServiceFactory.getInstance().getService().sendSms(endUser.getMobileNumber(),
										smsTemplate.replace("Helloooo", smsTemplate));
								return new ModelAndView("endUser/endUserBill", "endUserForm", endUser);
							} else {
								// Print some error
								System.out.println("4=Fail");
							}
						} 
					} else {
						// Show some error message
						return new ModelAndView("paymentGatewayDetails/paymentGatewayDetailsresponseFailed", "data",
								map);
					}
					return new ModelAndView("paymentGatewayDetails/paymentGatewayDetailsresponseFailed", "data", map);

				} else {
					map.put("message_error", "Failed to find EndUser.");
					return new ModelAndView("paymentGatewayDetails/paymentGatewayDetailsresponseFailed", "data", map);
				}
			} else {
				map.put("message_error", "Failed to find payment gateway details.");
				return new ModelAndView("endUser/createEndUser", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		
	}
}