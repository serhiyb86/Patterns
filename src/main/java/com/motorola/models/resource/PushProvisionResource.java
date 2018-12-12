package com.motorola.models.resource;

import com.motorola.models.representation.ProvisionItemList;
import org.restlet.resource.Post;

public interface PushProvisionResource {

	/**
	 * Receives provision lookup item update request - insert or update.
	 *
	 * @throws org.restlet.resource.ResourceException if the call to the API fails
	 */
	@Post
	void provisionItemUpdate(ProvisionItemList bean);

}