import axios from "axios";

let weatherOpenRequest = axios.create({
    baseURL: 'http://localhost:8765',
});
export default weatherOpenRequest;