package nl.retail.store.grocerystore.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateUtilTests {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void toLocalDateTest_Ok() {
        LocalDate date = DateUtil.toLocalDate("31/03/2021");
        assertEquals(LocalDate.of(2021, 3, 31), date);
    }

    @Test
    public void toLocalDateTest_Nok() {
        exception.expect(DateTimeParseException.class);
        DateUtil.toLocalDate("03/31/2021");
    }
}
