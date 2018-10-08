package com.models.resource;

import com.models.representation.ApiResponse;
import com.models.representation.BookOnParameters;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;

public interface UserSessionResource {

	/**
	 * Perform user book on CAD function.
	 *
	 * @return  {@link ApiResponse}
	 * @throws ResourceException if the call to the API fails
	 */
	@Post
	ApiResponse bookOn(BookOnParameters bean);

}