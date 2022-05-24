package org.oobootcamp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OOBootcampTest {

    @Test
    void should_welcome_to_oo_bootcamp() {
        OOBootcamp ooBootcamp = new OOBootcamp("Hello, Welcome to OOBootcamp");
        assertThat(ooBootcamp.message()).isEqualTo("Hello, Welcome to OOBootcamp");
    }

}
