import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;

public class MatrixRelationsTests {
    @Test
    public void testAddInput() {
        int[][] adjMatrix = {
                {0,6,2,0,0,12,0,0},
                {6,0,0,7,0,9,0,0},
                {2,0,0,0,1,2,0,0},
                {0,7,0,0,0,0,0,0},
                {0,0,1,0,0,0,6,8},
                {12,9,2,0,0,0,0,0},
                {0,0,0,0,6,0,0,4},
                {0,0,0,0,8,0,4,0}
        };
        MatrixRelations matrixRelations = new MatrixRelations();
        matrixRelations.createGeneralComponentSet(adjMatrix,8,7, new HashSet<>(Arrays.asList("A","B","C","D","E","F","G","H")));
        matrixRelations.relatedComponents(adjMatrix);
        matrixRelations.commonComponentGenerator();
        matrixRelations.getAllComponents();
    }
}
