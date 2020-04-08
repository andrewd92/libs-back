package com.andrewd.libs.funcTest.profile.controller;

import com.andrewd.libs.profile.domain.Profile;
import com.andrewd.libs.profile.repository.ProfileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProfileControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void getByIdReturnProfileJson() throws Exception {
        long id = 1;
        Profile expectedProfile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can not find profile in database"));

        mvc
            .perform(get("/v1/profile/" + id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.userId", is(((int) expectedProfile.getUserId()))))
            .andExpect(jsonPath("$.name", is(expectedProfile.getName())));
    }
}
