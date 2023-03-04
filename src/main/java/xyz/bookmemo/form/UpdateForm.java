package xyz.bookmemo.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateForm {

  @NotEmpty(message = "Username is empty")
  @Size(min = 2, max = 30, message = "2文字以上30文字以下で入力して下さい")
  @Pattern(
    regexp = "^[a-zA-Z0-9.?\\/-]{2,30}$",
    message = "半角英数字で入力して下さい"
  )
  private String accountName;

  @NotEmpty(message = "Email is empty")
  @Size(min = 2, max = 128, message = "2文字以上128文字以下で入力して下さい")
  @Pattern(
    regexp = "^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,128}$",
    message = "Emailの形式で入力して下さい"
  )
  private String email;

  @NotEmpty(message = "Current password is empty")
  @Size(min = 8, max = 50, message = "8文字以上50文字以下で入力して下さい")
  @Pattern(
    regexp = "^[a-zA-Z0-9.?\\/-]{8,50}$",
    message = "半角英数字で入力して下さい"
  )
  private String oldPassword;

  @NotEmpty(message = "New password is empty")
  @Size(min = 8, max = 50, message = "8文字以上50文字以下で入力して下さい")
  @Pattern(
    regexp = "^[a-zA-Z0-9.?\\/-]{8,50}$",
    message = "半角英数字で入力して下さい"
  )
  private String newPassword;

  @NotEmpty(message = "Confirm password is empty")
  @Size(min = 8, max = 50, message = "8文字以上50文字以下で入力して下さい")
  @Pattern(
    regexp = "^[a-zA-Z0-9.?\\/-]{8,50}$",
    message = "半角英数字で入力して下さい"
  )
  private String rePassword;

  public void resetPassword() {
    oldPassword = "";
    newPassword = "";
    rePassword = "";
  }
}
