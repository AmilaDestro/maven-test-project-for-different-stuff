package soloviova.mila.main.hashcode;

import lombok.val;

import java.util.HashMap;

/**
 * Practical class which demonstrates why it is important to override both equals() and hashcode() methods
 */
public class HashMain {
    public static void main(String[] args) {
        val person = new Person();
        person.setName("Liuda");
        person.setAge(32);
        person.setProfession("sdet");

        val map = new HashMap<Person, Integer>();
        map.put(person, 1);

        val person2 = new Person();
        person2.setProfession("sdet");
        person2.setAge(32);
        person2.setName("Liuda");

        map.put(person2, 2);

        System.out.println(person.equals(person2));
        System.out.println(person.hashCode());
        System.out.println(person2.hashCode());
        System.out.println(map.size());
    }
}
