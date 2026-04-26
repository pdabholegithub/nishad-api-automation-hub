import com.intuit.karate.junit5.Karate;

class TestRunner {
    
    @Karate.Test
    Karate testCourses() {
        return Karate.run("courses").relativeTo(getClass());
    }    

}
