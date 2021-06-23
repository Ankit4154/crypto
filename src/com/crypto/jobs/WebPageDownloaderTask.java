package com.crypto.jobs;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.crypto.dao.BookmarkDao;
import com.crypto.entities.WebLink;
import com.crypto.entities.WebLink.DownloadStatus;
import com.crypto.util.HttpConnect;

public class WebPageDownloaderTask implements Runnable{

	private static BookmarkDao dao = new BookmarkDao();
	private static final long TIME_FRAME = 3000000000L;
	private boolean donwloadAll = false;
	
	ExecutorService downloadExecutor = Executors.newFixedThreadPool(5);
	
	private static class Downloader<T extends WebLink> implements Callable<T>{
		private T webLink;
		public Downloader(T webLink){
			this.webLink = webLink;
		}
		public T call() {
			try {
				if(!webLink.getUrl().endsWith(".pdf")) {
					webLink.setDownloadStatus(DownloadStatus.FAILED);
					String htmlPage = HttpConnect.download(webLink.getUrl());
					webLink.setHtmlPage(htmlPage);
				}else {
					webLink.setDownloadStatus(DownloadStatus.NOT_ELIGIBLE);
				}
			}catch(MalformedURLException e) {
				e.printStackTrace();
			}catch(URISyntaxException e) {
				e.printStackTrace();
			}
			return webLink;
		}
	}
	
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			// Get Weblinks

			// Download concurrently
			
			// Wait
		}
		System.out.println("hello");
		downloadExecutor.shutdown();
	}
	
	public WebPageDownloaderTask(boolean downloadAll) {
		this.donwloadAll = downloadAll;
	}
	
}
