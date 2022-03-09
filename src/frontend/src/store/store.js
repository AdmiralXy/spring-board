import auth from "@/store/auth";
import init from "@/store/init";
import preloader from "@/store/preloader";
import {createStore} from "vuex";

export default createStore({
    modules: {
        auth, init, preloader
    }
})