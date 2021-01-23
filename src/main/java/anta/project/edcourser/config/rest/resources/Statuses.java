package anta.project.edcourser.config.rest.resources;

public enum Statuses {
    Ok(0),
    InvalidCredentials(1),
    Invalid2FA(2),
    LoginTaken(3),
    EmailTaken(4),
    WeakPassword(5),
    RegistrationFailed(6),
    UserCreationFailed(7),
    AuthenticationFailed(8),
    AuthorizationFailed(9),
    UserNotExists(10),
    TokenNotCreated(11),
    ChangeMailFailed(12),
    ChangePasswordFailed(13),
    UnexpectedError(14),
    InvalidHash(15),
    InvalidUser(16),
    InvalidUserData(17),
    InvalidExtension(18),
    UploadFileIsEmpty(19),
    InvalidToken(20),
    BadRole(21),
    FileNotFound(22),
    InvalidImage(23),
    TelegramNotConnected(24),
    TelegramAlreadyConnected(25),
    IdentifierNotFound(26),
    BannedIp(27),
    LimitReached(28),
    UnauthorizedRequest(29),
    EmailNotConfirmed(30),
    NotificationNotSelected(31),
    SupportTimeout(32),
    SettingsNotExists(33),
    NotImplemented(34);

    private int status;

    Statuses(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
