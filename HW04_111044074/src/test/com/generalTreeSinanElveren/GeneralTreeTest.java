package test.com.generalTreeSinanElveren; 

import com.generalTreeSinanElveren.GeneralTree;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;

/** 
* GeneralTree Tester. 
* 
* @author Sinan Elveren - Gebze Technical University
* @since <pre>Apr 16, 2018</pre> 
* @version 1.0 
*/ 
public class GeneralTreeTest extends GeneralTree{

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: add(E parentItem, E childItem) 
* @throws Exception if any addition error
*/ 
@Test
public void testAddForParentItemChildItem() throws Exception {
    GeneralTree<String> testTree = new GeneralTree<>();
    boolean isAdd = false;

    assertNotEquals(true, isAdd);

    //add to root
    testTree.add("Sinan", "Junior Sinan");

    //add to child's child
    isAdd = testTree.add("Sinan", "Junior Sinan II");

    assertEquals("Sinan", testTree.getData().toString());
    assertEquals("Junior Sinan", testTree.getLeftSubtree().getData().toString());
    assertEquals("Junior Sinan II", testTree.getLeftSubtree().getRightSubtree().getData().toString());

    assertEquals(true, isAdd);
}

/** 
* 
* Method: postOrderSearch(Node node, E target)
* 
*/ 
@Test
public void testPostOrderSearch() {

    GeneralTree<String> testTree = new GeneralTree<>();

    //add to root
    testTree.add("Sinan", "Junior Sinan");
    assertEquals("Sinan", testTree.getSearchReturn().data);

    testTree.add("Sinan", "Junior Sinan II");
    assertEquals("Sinan", testTree.getSearchReturn().data);

    testTree.add("Junior Sinan II", "Junior");
    assertEquals("Junior Sinan II", testTree.getSearchReturn().data);
} 

/** 
* 
* Method: levelOrderSearch(Node node, E target)
* 
*/ 
@Test
public void testLevelOrderSearch() {
    GeneralTree<String> testTree = new GeneralTree<>();

    testTree.testLevelOrder = true;
    //add to root
    testTree.add("Level 1", "Level 2");
    testTree.add("Level 2", "Level 3");
    testTree.add("Level 3", "Level 4");
    testTree.add("Level 3", "Level 4_");
    testTree.add("Level 2", "Level 3_");
    testTree.add("Level 4", "Level 5");

    System.out.println("\nFirstly, check child of parent(ayni elemandan eklemek icin). And check parent to add");

}


}
