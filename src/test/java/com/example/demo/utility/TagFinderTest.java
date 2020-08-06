package com.example.demo.utility;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TagFinderTest {

    @Mock
    private UserRepository userRepository;


    @Test
    public void findTagsTest() {
        //Given
        String content = "@existUser 내용 @nonExistUser 내용";

        //When
        List<String> tags = TagFinder.findTags(content);

        //Then
        assertThat(tags).hasSize(2);
    }
}
