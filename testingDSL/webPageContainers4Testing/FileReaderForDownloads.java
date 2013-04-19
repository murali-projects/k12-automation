package webPageContainers4Testing;

import java.io.File;

public class FileReaderForDownloads {

	private static final String ABSOLUTE_PATH = "D:\\CengageNew\\";

	public static String getFileFromDirectory(File path) {
		File files[];
		files = new File(ABSOLUTE_PATH).listFiles();
		return new File(files[0].getPath()).getName();
	}

	public static String getFileName(String fileName) {

		File tmpFile = new File(fileName);
		return tmpFile.getName();
	}

	public static String getFileExtension(String fileName) {

		File downLoadFile = new File(fileName);
		int dotPosition = downLoadFile.getName().lastIndexOf('.');
		if (0 < dotPosition) {
			return downLoadFile.getName().substring(dotPosition + 1);
		}
		return "";
	}

	public static String getFileAbsolutePathToDownload(String downloadFileName) {
		String fileFullPath = ABSOLUTE_PATH + downloadFileName+".html";
		String filePath = ABSOLUTE_PATH + downloadFileName;
		File file = new File(fileFullPath);
		new File(ABSOLUTE_PATH).mkdir();
		if (file.exists())
			file.delete();
		return filePath;
	}
	public static String getFileAbsolutePathToDownloadForPdf(String downloadFileName) {
		String fileFullPath = ABSOLUTE_PATH + downloadFileName+".pdf";
		String filePath = ABSOLUTE_PATH + downloadFileName;
		File file = new File(fileFullPath);
		new File(ABSOLUTE_PATH).mkdir();
		if (file.exists())
			file.delete();
		return filePath;
	}
}
