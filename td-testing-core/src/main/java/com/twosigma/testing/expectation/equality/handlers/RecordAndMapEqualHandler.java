package com.twosigma.testing.expectation.equality.handlers;

import java.util.Map;

import com.twosigma.testing.data.table.Record;
import com.twosigma.testing.expectation.ActualPath;
import com.twosigma.testing.expectation.equality.EqualComparator;
import com.twosigma.testing.expectation.equality.EqualComparatorHandler;

/**
 * @author mykola
 */
public class RecordAndMapEqualHandler implements EqualComparatorHandler {
    @Override
    public boolean handle(Object actual, Object expected) {
        return actual instanceof Record && mapWithStringKeys(expected);
    }

    private boolean mapWithStringKeys(final Object expected) {
        return expected instanceof Map &&
                ((Map<?, ?>) expected).keySet().stream().allMatch(k -> k instanceof String);

    }

    @Override
    public void compare(EqualComparator equalComparator, ActualPath actualPath, Object actual, Object expected) {
        Record actualRecord = (Record) actual;
        Map expectedMap = (Map) expected;

        for (Object key : expectedMap.keySet()) {
            String name = key.toString();
            final ActualPath propertyPath = actualPath.property(name);

            if (actualRecord.getHeader().has(name)) {
                final Object actualValue = actualRecord.get(name);
                final Object expectedValue = expectedMap.get(name);
                equalComparator.compare(propertyPath, actualValue, expectedValue);
            } else {
                equalComparator.reportMismatch(this, actualPath, propertyPath + " is not found");
            }
        }
    }
}
