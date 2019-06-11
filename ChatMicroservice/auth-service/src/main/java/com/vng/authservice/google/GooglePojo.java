package com.vng.authservice.google;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GooglePojo {

	private String id;
	private String email;
	private boolean verified_email;
	private String name;
	private String given_name;
	private String family_name;
	private String link;
	private String picture;

	public String toString() {
		return
		"GooglePojo [id=" + this.id + ", email=" + this.email + ", verified_email=" + this.verified_email + ", name="
				+ this.name + ", given_name=" + this.given_name + ", family_name=" + this.family_name + "]";
	}
}
