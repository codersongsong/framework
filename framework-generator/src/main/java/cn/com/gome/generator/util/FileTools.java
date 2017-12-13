package cn.com.gome.generator.util;

import java.io.*;

/**
 * <Description> 文件操作工具类 </Description>
 * <ClassName> FileTools</ClassName>
 *
 * @Author liuxianzhao
 * @Date 2017年12月06日 17:06
 */
public class FileTools {
	
	/**
	* @Title: fileCreate 
	* @Description: 文件创建
	* @param filePaht 存放路径
	* @param fileName 文件名称
	* @param context 内容
	* @return void    返回类型 
	* @throws
	 */
	public static void fileCreate(String filePaht, String fileName, String context) {
		File file = new File(filePaht);
		if(!file.exists()  && !file .isDirectory()){       
		    file.mkdirs();    
		}
		try {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePaht + "//" + fileName), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(context);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建文件路径
	 *
	 * @param filePath
	 * @throws IOException
	 */
	public static void mkdirs(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 创建新文件
	 *
	 * @param filePath
	 * @throws IOException
	 */
	public static void createNewFile(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		}
	}

	/**
	 * 将String写入文件
	 *
	 * @param filePath
	 * @param content
	 * @throws IOException
	 */
	public static void writeToFile(String filePath, String content) throws IOException {
		//创建文件
		createNewFile(filePath);
		//写入文件
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
		BufferedWriter writer = new BufferedWriter(write);
		writer.write(content);
		writer.flush();
		writer.close();
	}

	/**
	 * 复制整个文件夹的内容(含自身)
	 *
	 * @param oldPath 准备拷贝的目录
	 * @param newPath 指定绝对路径的新目录
	 * @return
	 */
	public static void copyFolderWithSelf(String oldPath, String newPath) {
		try {
			new File(newPath).mkdirs(); //如果文件夹不存在 则建立新文件夹
			File dir = new File(oldPath);
			// 目标
			newPath += File.separator + dir.getName();
			File moveDir = new File(newPath);
			if (dir.isDirectory()) {
				if (!moveDir.exists()) {
					moveDir.mkdirs();
				}
			}
			String[] file = dir.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}
				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath +
							"/" +
							(temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) { //如果是子文件夹
					copyFolderWithSelf(oldPath + "/" + file[i], newPath);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 复制单个文件(可更名复制)
	 *
	 * @param oldPathFile 准备复制的文件源
	 * @param newPathFile 拷贝到新绝对路径带文件名(注：目录路径需带文件名)
	 * @return
	 */
	public static void CopySingleFile(String oldPathFile, String newPathFile) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPathFile);
			if (oldfile.exists()) { //文件存在时
				InputStream inStream = new FileInputStream(oldPathFile); //读入原文件
				FileOutputStream fs = new FileOutputStream(newPathFile);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; //字节数 文件大小
					//System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
