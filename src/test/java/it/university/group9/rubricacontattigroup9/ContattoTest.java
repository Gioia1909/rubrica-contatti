package it.university.group9.rubricacontattigroup9;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class ContattoTest {

    @Test
    public void testContattoConstructor() {
        // Test costruttore con parametri
        Contatto contatto = new Contatto("Debora", "Villano", Arrays.asList("3923816991"), Arrays.asList("deboravillano1@gmail.com"), "Gruppo9");
        
        assertEquals("Debora", contatto.getName());
        assertEquals("Villano", contatto.getSurname());
        assertEquals(Arrays.asList("3923816991"), contatto.getNumbers());
        assertEquals(Arrays.asList("deboravillano1@gmail.com"), contatto.getEmails());
        assertEquals("Gruppo9", contatto.getNote());
        assertFalse(contatto.isFav());
    }

    @Test
    public void testContattoDefaultConstructor() {
        // Test costruttore predefinito
        Contatto contatto = new Contatto();
        
        assertEquals("", contatto.getName());
        assertEquals("", contatto.getSurname());
        assertTrue(contatto.getNumbers().isEmpty());
        assertTrue(contatto.getEmails().isEmpty());
        assertEquals("", contatto.getNote());
    }

    @Test
    public void testSetFav() {
        // Test modifica stato "favorito"
        Contatto contatto = new Contatto("Arianna", "Paletta", Arrays.asList("3923816992"), Arrays.asList("ariannapaletta@gmail.com"), "Avellino");
        
        contatto.setFav(true);
        assertTrue(contatto.isFav());
        
        contatto.setFav(false);
        assertFalse(contatto.isFav());
    }

    @Test
    public void testCompareTo() {
        // Test ordinamento con compareTo
    /*Valore negativo: se contatto1 precede contatto2 nell'ordinamento (se il cognome di contatto1 viene prima alfabeticamente di quello di contatto2).
          *Zero: se i due contatti sono considerati uguali (per esempio, se hanno lo stesso cognome e lo stesso nome).
          * Valore positivo: se contatto1 segue contatto2 nell'ordinamento (per esempio, se il cognome di contatto1 viene dopo quello di contatto2).*/

        Contatto contatto1 = new Contatto("Greta", "Villano", Arrays.asList("111"), Arrays.asList("greta.villano@gmail.com"), "Gruppo10");
        Contatto contatto2 = new Contatto("Roberta", "Paletta", Arrays.asList("222"), Arrays.asList("roberta.paletta@example.com"), "Avella");

        assertTrue(contatto1.compareTo(contatto2) > 0);
        assertTrue(contatto2.compareTo(contatto1) < 0);
  
        Contatto contatto3 = new Contatto("Alessia", "Villano", Arrays.asList("333"), Arrays.asList("alessia.villano@example.com"), "Gruppo10");
        Contatto contatto4 = new Contatto("Alessia", "Villano", Arrays.asList("333"), Arrays.asList("alessia.villano@example.com"), "Gruppo10");    
        assertEquals(0, contatto4.compareTo(contatto3)); // Sono uguali
    }

    @Test
    public void testEquals() {
        // Test uguaglianza contatti
        Contatto contatto1 = new Contatto("Gioia", "Iannuzzi", Arrays.asList("111111"), Arrays.asList("gioia.iannuzzi@gmail.com"), "Gruppo11");
        Contatto contatto2 = new Contatto("Gioia", "Iannuzzi", Arrays.asList("111111"), Arrays.asList("gioia.iannuzzi@gmail.com"), "Gruppo11");
        
        assertTrue(contatto1.equals(contatto2)); // I contatti devono essere uguali
        
        Contatto contatto3 = new Contatto("Luigi", "Montonetti", Arrays.asList("222222"), Arrays.asList("luigi.montonetti@gmail.com"), "Collega");
        assertFalse(contatto1.equals(contatto3)); // Contatti diversi
    }

    @Test
    public void testHashCode() {
        // Test hashCode
        Contatto contatto1 = new Contatto("Ottavia", "Iannuzzi", Arrays.asList("12345"), Arrays.asList("ottavia.iannuzzi@gmail.com"), "Amica");
        Contatto contatto2 = new Contatto("Ottavia", "Iannuzzi", Arrays.asList("12345"), Arrays.asList("ottavia.iannuzzi@gmail.com"), "Amica");
        
        assertEquals(contatto1.hashCode(), contatto2.hashCode()); // Gli hashCode devono essere uguali se gli oggetti sono uguali
    }
}
