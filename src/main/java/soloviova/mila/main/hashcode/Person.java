package soloviova.mila.main.hashcode;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Person {
    private String name;
    private String profession;
    private int age;

//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof HashPerson)) {
//            return false;
//        }
//        final HashPerson hashPerson = (HashPerson) obj;
//        return hashPerson.getName().equals(this.name) && hashPerson.getAge() == this.age
//                && hashPerson.getProfession().equals(this.profession);
//    }
}
