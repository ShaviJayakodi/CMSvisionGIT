const form = document.getElementById('form');
const gradeCode = document.getElementById('gradeCode');
const gradeDes = document.getElementById('gradeDes');

form.addEventListener('submit' , e=> {
  e.preventDefault();

  validateInput();
});

const setError = (element , message) => {
  const inputControl = element.parentElement;
  const errorDisplay = inputControl.querySelector('.error');

  errorDisplay.innerText = message;
  inputControl.classList.add('error');
  inputControl.classList.remove('success'); 
}

const setSuccess = element => {
   const inputControl = element.parentElement;
   const errorDisplay = inputControl.querySelector('.error');

   errorDisplay.innerText = '';
   inputControl.classList.add('success');
   inputControl.classList.remove('error');
}


const validateInput = () => {
  const gradeCodeValue = gradeCode.value.trim();
  const gradeDes = gradeDes.value.trim();

  if(gradeCode === '')
  {
    setError(gradeCode, 'Grade Code is Required');
  }
  else
  {
    setSuccess(gradeCode);
  }

  if(gradeDes === '')
  {
    setError(gradeDes, 'Grade Description is Required');
  }
  else
  {
    setSuccess(gradeDes);
  }
};