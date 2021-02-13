package app.text_quest.exceptions;


import org.junit.jupiter.api.Test;

class ResourceNotFoundExceptionTest {
    @Test()
    public void test() throws ResourceNotFoundException {
        try {
            throw new ResourceNotFoundException("Message");
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}