package se.scrier.plugin.test.junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class RequiredAttributeExceptionTest {

	@SuppressWarnings("unused")
	@Test(expected=RequiredAttributeException.class)
	public void test() throws RequiredAttributeException {
		if( true ) {
			throw new RequiredAttributeException("haha");
		}
		fail("Should not come here");
	}
	
	@Test
	public void test2() {
		try {
			if( true ) {
				throw new RequiredAttributeException("haha");
			}
		} catch (Exception e) {
			assertEquals("haha", e.getMessage());
		}
	}

}
