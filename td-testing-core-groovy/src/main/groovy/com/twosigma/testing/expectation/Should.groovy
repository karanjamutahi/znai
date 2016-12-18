package com.twosigma.testing.expectation

import com.twosigma.testing.expectation.equality.EqualMatcher

/**
 * @author mykola
 */

class Should {
    private Object actual

    Should(Object actual) {
        this.actual = actual
    }

    boolean equals(Object expected) {
        // TODO later replace with AST
        // this method will only have exception
        // also this won't work if actual is null as it won't even reach this place
        ActualValue.value(actual).should(EqualMatcher.equal(expected))
        return true

        // throw new IllegalStateException("should not reach this place")
    }
}
