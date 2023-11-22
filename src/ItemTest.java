import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

//experimenting
class ItemTest {

    @Test
    void checkRoadWithW() {

        //arrange
        var position = new Coordinates(3, 3);

        Item mockedItem = mock(Item.class);
        when(mockedItem.checkRoad('w', position)).thenReturn(new Coordinates(position.x - 1, position.y));

        //act
        var expectedResultX = position.x - 1;
        var actualResultX = mockedItem.checkRoad('w', position).x;
        var expectedResultY = position.y;
        var actualResultY = mockedItem.checkRoad('w', position).y;

        //assert
        assertEquals(expectedResultX, actualResultX);
        assertEquals(expectedResultY, actualResultY);
    }

    @Test
    void checkRoadWithSpace() {

        //arrange
        var position = new Coordinates(3, 3);

        Item mockedItem = mock(Item.class);
        when(mockedItem.checkRoad(' ', position)).thenThrow(
               new IllegalArgumentException("Invalid command parameter passed to checkRoad method")
        );

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> mockedItem.checkRoad(' ', position));
    }

    @Test
    void checkRoadWithPossibleNegativeIndexOutcomeShouldReturnStartPoint() {

        //arrange
        var position = new Coordinates(1, 0);
        Item mockedItem = mock(Item.class);
        when(mockedItem.checkRoad('a', position)).thenReturn(position);

        //act
        var expectedResult = position;
        var actualResult = mockedItem.checkRoad('a', position);

        //assert
        assertEquals(expectedResult, actualResult);
    }

}