import axios from "axios";

let weather = axios.create({
    baseURL: 'https://lp1ocggb8f.execute-api.us-east-1.amazonaws.com/dev/api',
});
export default weather;