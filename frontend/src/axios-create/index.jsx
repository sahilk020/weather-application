import axios from "axios";

let weather = axios.create({
    baseURL: 'http://localhost:8765/api',
});
export default weather;