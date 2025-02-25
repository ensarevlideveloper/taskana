package pro.taskana.testapi;

import static pro.taskana.testapi.builder.ClassificationBuilder.newClassification;
import static pro.taskana.testapi.builder.ObjectReferenceBuilder.newObjectReference;
import static pro.taskana.testapi.builder.UserBuilder.newUser;
import static pro.taskana.testapi.builder.WorkbasketBuilder.newWorkbasket;

import java.util.Random;
import java.util.UUID;

import pro.taskana.testapi.builder.ClassificationBuilder;
import pro.taskana.testapi.builder.ObjectReferenceBuilder;
import pro.taskana.testapi.builder.UserBuilder;
import pro.taskana.testapi.builder.WorkbasketBuilder;
import pro.taskana.workbasket.api.WorkbasketType;

public class DefaultTestEntities {

  private DefaultTestEntities() {
    throw new IllegalStateException("Utility class");
  }

  public static ClassificationBuilder defaultTestClassification() {
    return newClassification()
        .key(UUID.randomUUID().toString().replace("-", ""))
        .domain("DOMAIN_A");
  }

  public static WorkbasketBuilder defaultTestWorkbasket() {
    return newWorkbasket()
        .key(UUID.randomUUID().toString())
        .domain("DOMAIN_A")
        .name("Megabasket")
        .type(WorkbasketType.GROUP)
        .orgLevel1("company");
  }

  public static ObjectReferenceBuilder defaultTestObjectReference() {
    return newObjectReference()
        .company("Company1")
        .system("System1")
        .systemInstance("Instance1")
        .type("Type1")
        .value("Value1");
  }

  public static UserBuilder randomTestUser() {
    return newUser()
        .id(UUID.randomUUID().toString().replace("-", ""))
        .firstName(RandomStringGenerator.generateRandomString(10))
        .lastName(RandomStringGenerator.generateRandomString(12));
  }

  private static class RandomStringGenerator {
    private static final Random RANDOM = new Random(15);

    private static String generateRandomString(int length) {
      // see ascii table for details number -> char conversion.
      return RANDOM
          .ints('0', 'z' + 1)
          .filter(i -> (i <= '9' || i >= 'A') && (i <= 'Z' || i >= 'a'))
          .limit(length)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
    }
  }
}
