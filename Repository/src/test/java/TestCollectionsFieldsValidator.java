import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.itis.model.CollectionsFieldsTest;
import ru.itis.model.Test2;
import ru.itis.validator.CollectionFieldsValidator;
import ru.itis.validator.CollectionFieldsValidatorImpl;

import java.util.*;

@RunWith(JUnit4.class)
public class TestCollectionsFieldsValidator {

    private CollectionFieldsValidatorImpl validator;
    private CollectionFieldsValidatorImpl validator2;

    public TestCollectionsFieldsValidator(){
        validator = new CollectionFieldsValidatorImpl();
        CollectionFieldsValidator ann = CollectionsFieldsTest.class.
                getAnnotation(CollectionFieldsValidator.class);
        validator.initialize(ann);

        validator2 = new CollectionFieldsValidatorImpl();
        CollectionFieldsValidator ann2 = Test2.class.
                getAnnotation(CollectionFieldsValidator.class);
        validator.initialize(ann2);
    }

    @Test
    public void emptyAndNullCollection(){
        CollectionsFieldsTest test = new CollectionsFieldsTest();
        Set<Integer> test2  = new HashSet<>();

        test.setTest2(test2);

        Assert.assertFalse(validator.isValid(test, null));
    }

    @Test
    public void filledAndEmptyCollection(){
        CollectionsFieldsTest test = new CollectionsFieldsTest();
        List<String> test1 = new ArrayList<>();
        Set<Integer> test2  = new HashSet<>();

        test1.add("test");

        test.setTest1(test1);
        test.setTest2(test2);

        Assert.assertFalse(validator.isValid(test, null));
    }

    @Test
    public void filledCollections(){
        CollectionsFieldsTest test = new CollectionsFieldsTest();
        List<String> test1 = new ArrayList<>();
        Set<Integer> test2  = new HashSet<>();

        test1.add("test");
        test2.add(5);

        test.setTest1(test1);
        test.setTest2(test2);

        Assert.assertTrue(validator.isValid(test, null));
    }

//    @Test
//    public void fieldNotExist(){
//    }
}
