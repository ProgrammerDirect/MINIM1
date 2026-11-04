package models;

public class UserNotFoundException extends Exception
{
    public UserNotFoundException(String message)
    {
        super("User not found: " + message);
    }
}
