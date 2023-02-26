package com.herokuapp.bookmemo.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class MemoForm {

  private Long memoId;
  // @org.springframework.data.relational.core.mapping.Embedded.Nullable
  // private Date createdDate;
  // @org.springframework.data.relational.core.mapping.Embedded.Nullable
  // private Date upDatedDate;

  @NotEmpty(message = "Title is empty.")
  @Size(min = 1, max = 30, message = "1文字以上30文字以下で入力して下さい")
  @Pattern(regexp = "[^!\"#$%&'()\\*\\+\\-\\.,\\/:;<=>?@\\[\\\\\\]^_`{|}~]+",
      message = "特殊記号は使えません")
  private String title;

  @NotEmpty(message = "Content is empty.")
  @Size(min = 1, max = 3000, message = "1文字以上3000文字以下で入力して下さい")
  @Pattern(regexp = "[^!\"#$%&'()\\*\\+\\-\\.,\\/:;<=>?@\\[\\\\\\]^_`{|}~]+",
      message = "特殊記号は使えません")
  private String content;

  @NotEmpty(message = "Category is empty.")
  @Size(min = 1, max = 30, message = "1文字以上30文字以下で入力して下さい")
  @Pattern(regexp = "[^!\"#$%&'()\\*\\+\\-\\.,\\/:;<=>?@\\[\\\\\\]^_`{|}~]+",
      message = "特殊記号は使えません")
  private String category;

  @NotEmpty(message = "Book name is empty.")
  @Size(min = 1, max = 30, message = "1文字以上30文字以下で入力して下さい")
  @Pattern(regexp = "[^!\"#$%&'()\\*\\+\\-\\.,\\/:;<=>?@\\[\\\\\\]^_`{|}~]+",
      message = "特殊記号は使えません")
  private String bookName;
}
