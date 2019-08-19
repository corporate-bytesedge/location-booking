import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class SyncApp2ApiDefinition {
	public static void main(String args[]) {
		try {
			File appDir = new File("src/main/java/com/shadkona/prms/model");
			File destDir = new File("dtoBuild/java/com/shadkona/prms/api/dto");
			if(destDir.exists()) {
				destDir.delete();
			}
			destDir.mkdirs();
			System.out.println("Created " + destDir.getAbsolutePath());
			
			System.out.println("Exploring beans in " + appDir.getAbsolutePath());
			if(appDir.exists()) {
				System.out.println("Exploring beans in " + appDir.getAbsolutePath());
				String[] beanList = appDir.list();
				for(String bean : beanList) {
					System.out.println(appDir + File.separator + bean);
					File beanFile = new File(appDir + File.separator + bean);
					if(beanFile.exists()) {
						FileOutputStream fout = new FileOutputStream(destDir.getAbsolutePath() + File.separator + bean);
						FileInputStream fin = new FileInputStream(beanFile);
						InputStreamReader isr = new InputStreamReader(fin);
						BufferedReader bir = new BufferedReader(isr);
						String line = null;
						System.out.println("Creating " + destDir.getAbsolutePath() + File.separator + bean);
						while((line = bir.readLine()) != null) {
							line = line.trim();
							if(line.startsWith("package com.bytesedge.bookvenue")) {
								fout.write("package com.bytesedge.bookvenue.api.dto;".getBytes());
								fout.write("\n".getBytes());
							} else if (line.startsWith("@")) {
							} else if (line.startsWith("import javax.persistence")) {
							} else if (line.startsWith("import javax.validation")) {
							} else if (line.startsWith("import org.springframework")) {
							} else if (line.startsWith("import com.fasterxml")) {
							} else if (line.startsWith("lazyModuleList")) {
							} else if (line.startsWith("import org.apache.commons.collections")) {
							} else if (line.startsWith("ObjectMapper")) {
							} else if (line.startsWith("return jsonApi.writeValueAsString(lazyModuleList);")) {
							} else if (line.startsWith("import org.hibernate.Hibernate;")) {
							} else if (line.startsWith("Class<?> src = Hibernate.getClass(this);")) {
							} else if (line.startsWith("Class<?> dst = Hibernate.getClass(obj);")) {
							} else if (line.startsWith("if (!src.getClass().getName().equals(dst.getClass().getName())) {return false;}")) {
							} else {
								fout.write(line.getBytes());
								fout.write("\n".getBytes());
							}
						}
						fout.flush();
						fout.close();
						bir.close();
					}
				}
			} else {
				System.out.println("Not exists ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}