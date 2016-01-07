package com.spacedebris.spacedebris;

public class FriendListModel {

	public String name;
	public String fbId;

	public String url;

	@Override
	public String toString() {
		return "FriendListModel [name=" + name + ", FB_ID=" + fbId + ", url="
				+ url + "]";
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the fB_ID
	 */
	public String getFB_ID() {
		return fbId;
	}

	/**
	 * @param fB_ID
	 *            the fB_ID to set
	 */
	public void setFB_ID(String fB_ID) {
		fbId = fB_ID;
	}

}
