package com.crypto.entities;

import org.apache.commons.lang3.StringUtils;

import com.crypto.partner.Shareable;

public class WebLink extends Bookmark implements Shareable {
	private String url;
	private String host;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return "WebLink [url=" + url + ", host=" + host + "]";
	}

	@Override
	public boolean isKidFriendly() {
		if (url.contains("horror") || this.getTitle().contains("horror") || host.contains("scary"))
			return false;
		return true;
	}

	@Override
	public String getItemData() {
		StringBuilder builder = new StringBuilder();
		builder.append("<item>");
			builder.append("<type>WebLink</title>");
			builder.append("<title>").append(this.getTitle()).append("</title>");
			builder.append("<url>").append(url).append("</url>");
			builder.append("<host>").append(host).append("</host>");
		builder.append("</item>");
		return builder.toString();
	}
}
