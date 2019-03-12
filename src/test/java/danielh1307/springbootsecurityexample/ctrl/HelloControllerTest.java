package danielh1307.springbootsecurityexample.ctrl;

import danielh1307.springbootsecurityexample.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrentUserProvider currentUserProvider;

    @Test
    public void hello_withAnyUser_callIsSuccessful() throws Exception {
        when(currentUserProvider.getCurrentUser()).thenReturn(new User("blah", "blah", true));

        this.mockMvc.perform(get("/hello").with(user("someone")))
                .andExpect(status().isOk());
    }

    // This test is not working with @WebMvcTest because SecurityContext is not available
    @Test
    public void everyone_withAnyUser_callIsSuccessful() throws Exception {
        when(currentUserProvider.getCurrentUser()).thenReturn(new User("blah", "blah", true));

        this.mockMvc.perform(get("/everyone").with(user("someone")))
                .andExpect(status().isOk());
    }

    @Test
    public void onlyWithRole_withAuthroizedUser_callIsSuccessful() throws Exception {
        when(currentUserProvider.getCurrentUser()).thenReturn(new User("blah", "blah", true));

        this.mockMvc.perform(get("/role").with(user("someone")))
                .andExpect(status().isOk());
    }

    // This test is not working with @WebMvcTest because @PreAuthorize is not evaluated correctly
    @Test
    public void onlyWithRole_withUnAuthroizedUser_callIsForbidden() throws Exception {
        when(currentUserProvider.getCurrentUser()).thenReturn(new User("blah", "blah", false));

        this.mockMvc.perform(get("/role").with(user("someone")))
                .andExpect(status().isForbidden());
    }
}
