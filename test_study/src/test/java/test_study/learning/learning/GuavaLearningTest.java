package test_study.learning.learning;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GuavaLearningTest {

    @DisplayName("")
    @Test
    void test() {
        //Given
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);

        //When
        List<List<Integer>> partition = Lists.partition(integers, 3);


        //Then
        assertThat(partition).hasSize(2)
                .isEqualTo(List.of(List.of(1, 2, 3), List.of(4, 5, 6)));

    }

    @DisplayName("")
    @Test
    void multiMapTest() {
        //Given
        ArrayListMultimap<@Nullable String, @Nullable String> multiMap = ArrayListMultimap.create();
        multiMap.put("커피", "아메리카노");
        multiMap.put("커피", "카페라떼");
        multiMap.put("커피", "에스프레소");
        multiMap.put("베이커리", "크루아상");
        multiMap.put("베이커리", "식방");

        //When
        Collection<String> strings = multiMap.get("커피");

        //Then
        assertThat(strings).hasSize(3)
                .isEqualTo(List.of("아메리카노", "카페라떼", "에스프레소"));


    }




    @DisplayName("")
    @TestFactory
    Collection<DynamicTest> multiMapTest2() {
        //Given
        ArrayListMultimap<@Nullable String, @Nullable String> multiMap = ArrayListMultimap.create();
        multiMap.put("커피", "아메리카노");
        multiMap.put("커피", "카페라떼");
        multiMap.put("커피", "에스프레소");
        multiMap.put("베이커리", "크루아상");
        multiMap.put("베이커리", "식방");

        return List.of(
                DynamicTest.dynamicTest("1개 value 삭제", () -> {
                    multiMap.remove("커피", "에스프레소");

                    Collection<String> results = multiMap.get("커피");
                    assertThat(results).hasSize(2)
                            .isEqualTo(List.of("아메리카노", "카페라떼"));

                }),
                DynamicTest.dynamicTest("일괄 삭제", () -> {
                    multiMap.removeAll("커피");

                    Collection<String> results = multiMap.get("커피");
                    assertThat(results).hasSize(0)
                            .isEqualTo(List.of());
                    assertThat(results.isEmpty()).isTrue();

                })
        );


    }
}
