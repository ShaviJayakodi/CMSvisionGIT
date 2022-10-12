function notFoundStudent()
{
    Swal.fire({
        icon: 'error',
        title: 'Student Not Found',
        text: 'Invalid Student Register Number!',
    });
}
function notFoundData()
{
    Swal.fire({
        icon: 'error',
        title: 'Data Not Found',
        text: 'Please Try Again!',
    });
}

function errorAlert()
{
    Swal.fire({
        icon: 'error',
        title: 'ERROR',
        text: 'Something went wrong!',

    });
}
