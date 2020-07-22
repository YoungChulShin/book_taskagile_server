package com.taskagile.domain.model.user;

import com.taskagile.domain.common.security.PasswordEncryptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RegistrationManagementTest {

  private UserRepository userRepositoryMock;
  private PasswordEncryptor passwordEncryptorMock;
  private RegistrationManagement instance;

  @Before
  public void setup() {
    userRepositoryMock = Mockito.mock(UserRepository.class);
    passwordEncryptorMock = Mockito.mock(PasswordEncryptor.class);
    instance = new RegistrationManagement(userRepositoryMock, passwordEncryptorMock);
  }

  // 이미 존재하는 사용자명을 갖는 사용자를 등록 시도할 때 실패하는가?
  @Test(expected = UsernameExistsException.class)
  public void register_existedUsername_shouldFail() throws  RegistrationException {
    String username = "youngchulshin";
    String emailAddress = "go1323@gmail.com";
    String password = "1234567";

    // 특정 사용자로 조회가 되면 null이 아니라 인스턴스를 리턴하도록 처리
    Mockito.when(userRepositoryMock.findByUsername(username)).thenReturn(new User());

    // 등록 진행
    instance.register(username, emailAddress, password);
  }

  // 이미 존재하는 이메일을 갖는 사용자를 등록 시도할 때 실패하는가?
  @Test(expected = EmailAddressExistsException.class)
  public void register_existedEmailAddress_shouldFail() throws RegistrationException {
    String username = "youngchulshin";
    String emailAddress = "go1323@gmail.com";
    String password = "1234567";

    // 특정 사용자로 조회가 되면 null이 아니라 인스턴스를 리턴하도록 처리
    Mockito.when(userRepositoryMock.findByEmailAddress(emailAddress)).thenReturn(new User());

    // 등록 진행
    instance.register(username, emailAddress, password);
  }

  // TODO: 유요한 데이터를 갖는 사용자를 등록 시도할 때 비밀번호가 암호화되는지 확인하기

  // 저장소의 모든 사용자의 이메일주소가 소문자로 저장되는지 확인하기
  @Test
  public void register_uppercaseEmailAddress_shloudSucceedBecomeLowerCase() throws RegistrationException {
    // 대문자로 저장
    String username = "youngchulshin";
    String emailAddress = "GO1323@Gmail.com";
    String password = "myPassword";

    instance.register(username, emailAddress, password);

    // 소문자 유저 생성
    User userToSave = User.create(username, emailAddress.toLowerCase(), password);

    // 해당 인스턴스로 저장이 한번 발생했는지 테스트
    Mockito.verify(userRepositoryMock).save(userToSave);
  }

  @Test
  public void register_newUser_shouldSucceed() throws RegistrationException {
    String username = "youngchulshin";
    String emailAddress = "go1323@gmail.com";
    String password = "myPassword";
    String encryptedPassword = "EcryptedPassword";
    User newUser = User.create(username, emailAddress, encryptedPassword);

    // 등록을 위한 동작이 수행될 때 아무것도 하지 않도록 설정
    Mockito.when(userRepositoryMock.findByUsername(username)).thenReturn(null);
    Mockito.when(userRepositoryMock.findByEmailAddress(emailAddress)).thenReturn(null);
    Mockito.when(userRepositoryMock.save(newUser)).thenReturn(newUser);

    Mockito.when(passwordEncryptorMock.encrypt(password))
      .thenReturn(encryptedPassword);

    // userRepositoryMock 검증 - API 들이 순차적으로 모두 수행되었는가?
    instance.register(username, emailAddress, password);
    InOrder inOrder = Mockito.inOrder(userRepositoryMock);
    inOrder.verify(userRepositoryMock).findByUsername(username);
    inOrder.verify(userRepositoryMock).findByEmailAddress(emailAddress);
    inOrder.verify(userRepositoryMock).save(newUser);

    // 암호화 코드가 수행되었는지
    Mockito.verify(passwordEncryptorMock).encrypt(password);
  }
}
