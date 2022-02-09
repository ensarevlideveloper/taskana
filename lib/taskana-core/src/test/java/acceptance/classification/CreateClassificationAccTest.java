package acceptance.classification;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import acceptance.DefaultTestEntities;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.function.ThrowingConsumer;
import testapi.TaskanaInject;
import testapi.TaskanaIntegrationTest;

import pro.taskana.classification.api.ClassificationService;
import pro.taskana.classification.api.exceptions.ClassificationAlreadyExistException;
import pro.taskana.classification.api.exceptions.MalformedServiceLevelException;
import pro.taskana.classification.api.models.Classification;
import pro.taskana.classification.api.models.ClassificationSummary;
import pro.taskana.classification.internal.models.ClassificationImpl;
import pro.taskana.common.api.TaskanaEngine;
import pro.taskana.common.api.exceptions.DomainNotFoundException;
import pro.taskana.common.api.exceptions.InvalidArgumentException;
import pro.taskana.common.api.exceptions.NotAuthorizedException;
import pro.taskana.common.test.security.WithAccessId;

/** Acceptance test for all "create classification" scenarios. */
@TaskanaIntegrationTest
class CreateClassificationAccTest {

  @TaskanaInject ClassificationService classificationService;
  @TaskanaInject TaskanaEngine taskanaEngine;

  @WithAccessId(user = "businessadmin")
  @Test
  void should_OnlyCreateOneClassification_WhenCreatingMasterClassification() throws Exception {
    Classification classification = classificationService.newClassification("Key0", "", "TASK");

    classification = classificationService.createClassification(classification);

    List<ClassificationSummary> classifications =
        classificationService.createClassificationQuery().keyIn("Key0").list();
    assertThat(classifications).containsExactly(classification.asSummary());
  }

  @WithAccessId(user = "businessadmin")
  @Test
  void should_CreateMasterClassification_WhenCreatingClassificationWithDomain() throws Exception {
    Classification classification =
        classificationService.newClassification("Key1", "DOMAIN_A", "TASK");

    classificationService.createClassification(classification);

    ClassificationImpl expectedMasterClassification =
        (ClassificationImpl) classification.copy("Key1");
    expectedMasterClassification.setDomain("");

    List<ClassificationSummary> classifications =
        classificationService.createClassificationQuery().keyIn("Key1").list();

    assertThat(classifications)
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
        .containsExactlyInAnyOrder(
            classification.asSummary(), expectedMasterClassification.asSummary());
  }

  @WithAccessId(user = "businessadmin")
  @Test
  void should_CreateClassification_WhenAlreadyExistingMasterClassification() throws Exception {

    Classification masterClassification =
        DefaultTestEntities.defaultTestClassification()
            .key("Key3")
            .domain("")
            .type("TASK")
            .buildAndStore(classificationService);

    Classification classification =
        classificationService.newClassification("Key3", "DOMAIN_B", "TASK");
    classificationService.createClassification(classification);

    List<ClassificationSummary> classifications =
        classificationService.createClassificationQuery().keyIn("Key3").list();
    assertThat(classifications).contains(classification.asSummary());
  }

  @WithAccessId(user = "businessadmin")
  @TestFactory
  Stream<DynamicTest>
      should_ThrowException_When_TryingToCreateClassificationWithInvalidServiceLevel() {
    Iterator<String> iterator = Arrays.asList("P-1D", "abc").iterator();
    ThrowingConsumer<String> test =
        invalidServiceLevel -> {
          Classification classification =
              classificationService.newClassification("KeyErrCreation", "DOMAIN_A", "TASK");
          classification.setServiceLevel(invalidServiceLevel);

          assertThatThrownBy(() -> classificationService.createClassification(classification))
              .isInstanceOf(MalformedServiceLevelException.class)
              .hasMessageContaining("KeyErrCreation", invalidServiceLevel, "DOMAIN_A");
        };

    return DynamicTest.stream(iterator, c -> String.format("for '%s'", c), test);
  }

  @WithAccessId(user = "businessadmin")
  @Test
  void should_ThrowException_When_TryingToCreateClassificationWithInvalidKey() {
    Classification classificationWithNullKey =
        classificationService.newClassification(null, "DOMAIN_A", "TASK");

    assertThatThrownBy(() -> classificationService.createClassification(classificationWithNullKey))
        .isInstanceOf(InvalidArgumentException.class)
        .hasMessage("Classification must contain a key");
  }

  @WithAccessId(user = "businessadmin")
  @Test
  void should_ThrowException_TryingToCreateClassificationWithInvalidDomain() {
    String invalidDomainName = "UNKNOWN_DOMAIN";

    Classification classification =
        classificationService.newClassification("KeyErrCreation", invalidDomainName, "TASK");

    assertThatThrownBy(() -> classificationService.createClassification(classification))
        .isInstanceOf(DomainNotFoundException.class)
        .hasMessage(
            String.format("Domain '%s' does not exist in the configuration", invalidDomainName));
  }

  @WithAccessId(user = "businessadmin")
  @Test
  void should_ThrowException_TryingToCreateClassificationWithInvalidType() {
    String invalidType = "UNKNOWN_TYPE";

    Classification classification =
        classificationService.newClassification("KeyErrCreation", "DOMAIN_A", invalidType);

    assertThatThrownBy(() -> classificationService.createClassification(classification))
        .isInstanceOf(InvalidArgumentException.class)
        .hasMessage(
            String.format(
                "Given classification type %s is not valid according to the configuration.",
                invalidType));
  }

  @WithAccessId(user = "businessadmin")
  @Test
  void should_ThrowException_TryingToCreateClassificationWithInvalidCategory() {
    String invalidCategory = "UNKNOWN_CATEGORY";

    Classification classification =
        classificationService.newClassification("KeyErrCreation", "DOMAIN_A", "TASK");
    classification.setCategory(invalidCategory);

    assertThatThrownBy(() -> classificationService.createClassification(classification))
        .isInstanceOf(InvalidArgumentException.class)
        .hasMessage(
            String.format(
                "Given classification category %s with type TASK is not valid according to the"
                    + " configuration.",
                invalidCategory));
  }

  @WithAccessId(user = "businessadmin")
  @Test
  void should_ThrowException_TryingToCreateClassificationWithInvalidParentKey() {
    String invalidParentKey = "UNKNOWN_KEY";

    Classification classification =
        classificationService.newClassification("KeyErrCreation", "", "TASK");
    classification.setParentKey(invalidParentKey);

    // Make sure that there is no (Parent) Classification with this Key.
    List<ClassificationSummary> classifications =
        classificationService.createClassificationQuery().keyIn(invalidParentKey).list();
    assertThat(classifications).isEmpty();
    assertThatThrownBy(() -> classificationService.createClassification(classification))
        .isInstanceOf(InvalidArgumentException.class)
        .hasMessage("Parent classification could not be found.");
  }

  @WithAccessId(user = "businessadmin")
  @Test
  void should_ThrowException_TryingToCreateClassificationWithInvalidParentId() {
    String invalidParentId = "UNKNOWN_ID";

    Classification classification = classificationService.newClassification("KeyErr", "", "TASK");
    classification.setParentId(invalidParentId);

    // Make sure that there is no (Parent) Classification with this Id.
    List<ClassificationSummary> classifications =
        classificationService.createClassificationQuery().idIn(invalidParentId).list();
    assertThat(classifications).isEmpty();
    assertThatThrownBy(() -> classificationService.createClassification(classification))
        .isInstanceOf(InvalidArgumentException.class)
        .hasMessage("Parent classification could not be found.");
  }

  @WithAccessId(user = "businessadmin")
  @Test
  void should_ThrowException_TryingToCreateClassificationWithExplicitId() {
    String explicitId = "EXPLICIT ID";

    ClassificationImpl classification =
        (ClassificationImpl) classificationService.newClassification("KeyErrCreation", "", "TASK");
    classification.setId(explicitId);

    assertThatThrownBy(() -> classificationService.createClassification(classification))
        .isInstanceOf(InvalidArgumentException.class)
        .hasMessage("ClassificationId should be null on creation");
  }

  @WithAccessId(user = "taskadmin")
  @WithAccessId(user = "user-1-1")
  @TestTemplate
  void should_ThrowException_When_UserRoleIsNotAdminOrBusinessAdmin() {
    Classification classification =
        classificationService.newClassification("KeyErrCreation", "", "TASK");

    assertThatThrownBy(() -> classificationService.createClassification(classification))
        .isInstanceOf(NotAuthorizedException.class)
        .hasMessage(
            String.format(
                "Not authorized. The current user '%s' is not member of role(s) '[BUSINESS_ADMIN,"
                    + " ADMIN]'.",
                taskanaEngine.getCurrentUserContext().getUserid()));
  }

  @WithAccessId(user = "businessadmin")
  @Test
  void should_ThrowException_WhenClassificationWithKeyAlreadyExisting() throws Exception {
    String existingKey = "Key4";
    DefaultTestEntities.defaultTestClassification()
        .key(existingKey)
        .buildAndStore(classificationService);

    Classification classification =
        classificationService.newClassification(existingKey, "DOMAIN_A", "TASK");

    assertThatThrownBy(() -> classificationService.createClassification(classification))
        .isInstanceOf(ClassificationAlreadyExistException.class)
        .hasMessageContaining(
            String.format(
                "A Classification with key '%s' already exists in domain 'DOMAIN_A'.",
                existingKey));
  }

  @WithAccessId(user = "businessadmin")
  @Test
  void should_CreateNewClassification_When_ClassificationCopy() throws Exception {
    Classification classification =
        DefaultTestEntities.defaultTestClassification()
            .key("Key5")
            .buildAndStore(classificationService);

    Classification classificationCopy = classification.copy("Key5_copy");

    assertThatCode(() -> classificationService.createClassification(classificationCopy))
        .doesNotThrowAnyException();
  }

  @WithAccessId(user = "businessadmin")
  @TestFactory
  Stream<DynamicTest>
      should_SetDefaultServiceLevel_When_TryingToCreateClassificationWithMissingServiceLevel() {
    Iterator<String> iterator = Arrays.asList("", null).iterator();
    AtomicInteger i = new AtomicInteger();

    ThrowingConsumer<String> test =
        serviceLevel -> {
          Classification classification =
              classificationService.newClassification("Key6_" + i.getAndIncrement(), "", "TASK");
          classification.setServiceLevel(serviceLevel);

          classification = classificationService.createClassification(classification);

          assertThat(classification.getServiceLevel()).isEqualTo("P0D");
        };

    return DynamicTest.stream(iterator, c -> String.format("for '%s'", c), test);
  }

  @WithAccessId(user = "businessadmin")
  @Test
  void should_SetDefaultValues_When_CreatingClassificationWithoutSpecificValues() throws Exception {
    Classification classification = classificationService.newClassification("Key7", "", "TASK");
    classification = classificationService.createClassification(classification);

    assertThat(classification.getServiceLevel()).isEqualTo("P0D");
    assertThat(classification.getId()).isNotEqualTo(null);
    assertThat(classification.getId()).isNotEmpty();
    assertThat(classification.getCreated()).isNotNull();
    assertThat(classification.getModified()).isNotNull();
    assertThat(classification.getParentId()).isEqualTo("");
    assertThat(classification.getParentKey()).isEqualTo("");
    assertThat(classification.getIsValidInDomain()).isEqualTo(false);
  }

  @WithAccessId(user = "businessadmin")
  @AfterAll
  void should_NotCreateClassification_When_ExceptionIsThrownDuringCreation() {
    List<ClassificationSummary> classifications =
        classificationService.createClassificationQuery().keyIn("KeyErrCreation").list();

    assertThat(classifications).isEmpty();
  }

  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class CreatingChildClassificationTest {
    String parentId;
    String parentKey;

    @WithAccessId(user = "businessadmin")
    @BeforeAll
    void setupParentClassification() throws Exception {
      ClassificationSummary parentClassification =
          DefaultTestEntities.defaultTestClassification()
              .key("Key0")
              .domain("DOMAIN_A")
              .type("TASK")
              .buildAndStoreAsSummary(classificationService);
      parentId = parentClassification.getParentId();
      parentKey = parentClassification.getParentKey();
    }

    @WithAccessId(user = "businessadmin")
    @Test
    void should_CreateClassification_WhenCreatingChildClassification() throws Exception {
      Classification childClassification =
          classificationService.newClassification("Key0_1", "DOMAIN_A", "TASK");
      childClassification.setParentId(parentId);
      childClassification.setParentKey(parentKey);

      assertThatCode(() -> classificationService.createClassification(childClassification))
          .doesNotThrowAnyException();
      List<ClassificationSummary> classifications =
          classificationService.createClassificationQuery().parentIdIn(parentId).list();
      assertThat(classifications).contains(childClassification.asSummary());
    }

    @WithAccessId(user = "businessadmin")
    @Test
    void should_CreateMasterClassification_WhenCreatingChildClassification() throws Exception {
      Classification childClassification =
          classificationService.newClassification("Key0_2", "DOMAIN_A", "TASK");
      childClassification.setParentId(parentId);
      childClassification.setParentKey(parentKey);
      classificationService.createClassification(childClassification);

      ClassificationImpl expectedMasterClassification =
          (ClassificationImpl) childClassification.copy("Key0_2");
      expectedMasterClassification.setDomain("");
      expectedMasterClassification.setParentId(parentId);
      expectedMasterClassification.setParentKey(parentKey);
      List<ClassificationSummary> classifications =
          classificationService.createClassificationQuery().keyIn("Key0_2").list();
      assertThat(classifications)
          .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
          .contains(expectedMasterClassification.asSummary());
    }
  }
}
