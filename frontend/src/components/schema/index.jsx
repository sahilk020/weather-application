import * as Yup from 'yup'

export const registerSchema = Yup.object({
    firstName: Yup.string().min(3).max(30).required('Please enter your first name'),
    lastName:Yup.string().min(3).max(30).required('Please enter your last name'),
    email: Yup.string().email().required('Please enter your Email ID'),
    username: Yup.string().required('Please enter your username'),
    password: Yup.string().matches(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/).required('Please enter your desired password'),
    confirmPassword: Yup.string().oneOf([Yup.ref('password')],'Password should be same').required('Confirm password is required'),
    contactNumber: Yup.string().length(10).required('Please enter your contact number')
});
export const loginSchema = Yup.object({
    username:Yup.string().required('Please enter your username'),
    password:Yup.string().required('Please enter your password')
});