package com.example.range;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;

class RangeApplicationTests {

    @Test
    public void should_text_level_5() {


        Range open = Range.open(1, 5);
        assertThat(open.toString()).isEqualTo( "(1, 5)");

        Range closed = Range.closed(BigDecimal.valueOf(1.2345), BigDecimal.valueOf(3.45));
        assertThat(closed.toString()).isEqualTo( "[1.2345, 3.45]");

        Range openClosed = Range.openClosed(1, 5);
        assertThat(openClosed.toString()).isEqualTo( "(1, 5]");

        Range closedOpen = Range.closedOpen(1, 5);
        assertThat(closedOpen.toString()).isEqualTo( "[1, 5)");


        Range lessThan = Range.lessThan(5);
        assertThat(lessThan.toString()).isEqualTo( "[Infinitive, 5)");

        Range atLeastFive = Range.atLeast(5);
        assertThat(atLeastFive.toString()).isEqualTo( "[5, Infinitive]");

        Range atMostFive = Range.atMost(5);
        assertThat(atMostFive.toString()).isEqualTo( "[Infinitive, 5]");

        Range afterEpoch = Range.greaterThan(LocalDate.of(1900, Month.JANUARY, 1));
        assertThat(afterEpoch.toString()).isEqualTo( "(1900-01-01, Infinitive]");

        Range all = Range.all();
        assertThat(all.toString()).isEqualTo( "[Infinitive, Infinitive]");

    }

}
