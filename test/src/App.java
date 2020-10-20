import java.util.Optional;

public class App {

    public static void main(String[] args) throws Exception {
        Persona persona = new Persona();
        persona.setNombre("Jotaro");

        var optional = Optional.ofNullable(persona).map(person  -> {
            person.setNombre("");
            return person;
        });

        if(!optional.isPresent()) {
            System.out.println("vacio !!");
        }

        optional.stream().forEach(System.out::println);

    }
}
