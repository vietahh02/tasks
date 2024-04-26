function Validator(options) {

  let selectorRules = {};

  //Hàm thực hiện validate
  function validate(inputElement, rule) {
    let parentE = inputElement.parentElement;
    let errorElement = parentE.querySelector(options.errorSelector);

    //Lấy ra các rules của selector
    let rules = selectorRules[rule.selector];

    //lặp qua từng rule ()
    for(var i=0; i<rules.length; i++) {
        errorMessage = rules[i](inputElement.value);
        if(errorMessage) break;
    }

    if (errorMessage) {
      errorElement.innerText = errorMessage;
      parentE.classList.add("invalid");
    } else {
      errorElement.innerText = "";
      parentE.classList.remove("invalid");
    }
    return !errorMessage;
  }

  //Khi nhập thì không hiện lỗi nữa
  function onInput(inputElement) {
    let parentE = inputElement.parentElement;
    let errorElement = parentE.querySelector(options.errorSelector);
    errorElement.innerText = "";
    parentE.classList.remove("invalid");
  }

  //Lấy element của form
  let formElement = document.querySelector(options.form);
  if (formElement) {

    formElement.onsubmit = function (e) {
        e.preventDefault();

        let isFormValid = true;

        options.rules.forEach(function (rule) {
            let inputElement = formElement.querySelector(rule.selector);
            let isValid = validate(inputElement, rule);
            if(!isValid) {
                isFormValid = false;
            }
        });

        if(isFormValid) {
          formElement.submit();
        }

    };

    //Xử lý lặp qua mỗi rule và xử lý (lắng nghe event)
    options.rules.forEach(function (rule) {
      // Lưu lại các rules cho mỗi input
      if (Array.isArray(selectorRules[rule.selector])) {
        selectorRules[rule.selector].push(rule.test);
      } else {
        selectorRules[rule.selector] = [rule.test];
      }

      let inputElement = formElement.querySelector(rule.selector);

      if (inputElement) {
        //Xử lý trường hợp blur
        inputElement.onblur = function () {
          validate(inputElement, rule);
        };
        //Xử lý trường hợp nhập
        inputElement.oninput = function () {
          onInput(inputElement);
        };
      }
    });
  }
}

Validator.isRequired = function (selector) {
  return {
    selector: selector,
    test: function (value) {
      return value.trim() ? undefined : "Vui lòng nhập trường này";
    },
  };
};

Validator.isLength = function (selector, min = 6, max = 20) {
  return {
    selector: selector,
    test: function (value) {
      return value.length >= min && value.length <= max
        ? undefined
        : `Vui lòng nhập tối thiểu ${min} và tối đa là ${max} ký tự`;
    },
  };
};

// Validator.checkPassword = function (selector, min = 6, max = 20) {
//     return {
//         selector: selector,
//         test: function (value) {
//             regex = `/^[a-zA-Z0-9!@#$%^&*]{${min},${max}}$/`
//             return regex.
//     }
// }
