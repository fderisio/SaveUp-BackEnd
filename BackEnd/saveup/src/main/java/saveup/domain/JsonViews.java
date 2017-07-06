package saveup.domain;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Views for use with {@link JsonView @JsonView}.
 */
public interface JsonViews {

	interface Public {
	}

	interface NewUser extends Public {
	}

}
