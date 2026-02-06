package fr.extia.mentoring.fleetmanagement.services;

import fr.extia.mentoring.fleetmanagement.entities.Driver;
import fr.extia.mentoring.fleetmanagement.errors.NotFoundException;
import fr.extia.mentoring.fleetmanagement.repositories.DriverRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


// TODO Tester surtout le findById + Update  > Test avec coverage
public class DriverServiceTest {

    DriverRepository driverRepositoryMock = mock(DriverRepository.class);

    DriverService service = new DriverService(driverRepositoryMock);

    private static Stream<Driver> drivers() {
        var d1 = new Driver();
        var d2 = new Driver();
        var d3 = new Driver();
        var d4 = new Driver();
        var d5 = new Driver();

        d1.setId(1L);
        d2.setId(2L);
        d3.setId(3L);
        d4.setId(4L);
        d5.setId(5L);

        d1.setName("John");
        d2.setName("James");
        d3.setName("Jane");
        d4.setName("David");
        d5.setName("Billys");

        return Stream.of(d1, d2, d3, d5);
    }


    @Test
    void testFindAll() {
        // Given
        // Je ne comprends pas ce test > expected est forcement retourné dans actual
        // a cause de when(driverRepositoryMock.findAll()).thenReturn(expected);
        List<Driver> expected = drivers().toList();
        when(driverRepositoryMock.findAll()).thenReturn(expected);

        // When
        List<Driver> actual = service.findAll();

        // Then
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
        verify(driverRepositoryMock, times(1)).findAll();
    }

    @Test
    void testFindByIdIsKo() {
        // Given
        when(driverRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NotFoundException.class, () -> service.findById(1L));
    }

    @Test
    void testFindByIdIsOk() {
        // Given
        // Pourquoi le findAny().get() recupère le dirvers 1 ?
        Driver expected = drivers().findAny().get();
        when(driverRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expected));

        // When
        Driver actual = service.findById(1L);

        // Then
        assertEquals(expected, actual);
    }


    @Test
    void testUpdateIsKoNoId() {
        // Given
        Driver d = new Driver();
        d.setId(null);
        d.setName("Toto");

        // When & Then
        assertThrows(IllegalArgumentException.class,
                () -> service.update(d));

        // Vérifie le nombre de fois ou chaque fonction a été appelé
        verify(driverRepositoryMock, times(0)).findById(anyLong());
        verify(driverRepositoryMock, times(0)).save(any(Driver.class));
    }

    @Test
    void testUpdateIsKoNoDriver() {
        // Given
        Driver d = null;

        // When & Then
        assertThrows(IllegalArgumentException.class,
                () -> service.update(d));

        verify(driverRepositoryMock, times(0)).findById(anyLong());
        verify(driverRepositoryMock, times(0)).save(any(Driver.class));
    }


    @Test
    void testUpdateIsKoNotFoundInDatabase() {
        // Given
        Driver d = new Driver();
        d.setId(1L);
        d.setName("Toto");

        when(driverRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NotFoundException.class,
                () -> service.update(d));

        verify(driverRepositoryMock, times(1)).findById(anyLong());
        verify(driverRepositoryMock, times(0)).save(any(Driver.class));
    }


    @ParameterizedTest
    @MethodSource("updateParameters")
        // @Disabled
    void testUpdateIsOk(Driver paramDriver, Driver databaseDriver, Driver expectedSavedDriver) {
        // Given
        when(driverRepositoryMock.findById(anyLong()))
                .thenReturn(Optional.of(databaseDriver));
        when(driverRepositoryMock.save(any(Driver.class)))
                .thenAnswer(call -> call.getArgument(0, Driver.class));

        // When
        Driver actualSavedDriver = service.update(paramDriver);

        // Then
        assertEquals(expectedSavedDriver, actualSavedDriver);

        verify(driverRepositoryMock, times(1)).findById(anyLong());
        verify(driverRepositoryMock, times(1)).save(any(Driver.class));
    }

    static Stream<Arguments> updateParameters(){

        var paramDriver1 = new Driver();
        var paramDriver2 = new Driver();
        var paramDriver3 = new Driver();

        var expectedSavedDriver1 = new Driver();
        var expectedSavedDriver2 = new Driver();
        var expectedSavedDriver3 = new Driver();

        paramDriver1.setId(1L);
        expectedSavedDriver1.setId(1L);
        expectedSavedDriver1.setName("Toto");

        paramDriver2.setId(1L);
        paramDriver2.setName("Toto");
        expectedSavedDriver2.setId(1L);
        expectedSavedDriver2.setName("Toto");

        paramDriver3.setId(1L);
        paramDriver3.setName("Tata");
        expectedSavedDriver3.setId(1L);
        expectedSavedDriver3.setName("Tata");


        return Stream.of(
                Arguments.of(paramDriver1, new Driver(1L,"Toto"), expectedSavedDriver1),
                Arguments.of(paramDriver2, new Driver(1L,"Toto"), expectedSavedDriver2),
                Arguments.of(paramDriver3, new Driver(1L,"Toto"), expectedSavedDriver3)
                );
    }

}
