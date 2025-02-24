import { store } from "../GlobalData/store";

function Logout ()
{
    store.loggedInUser.userId = 0;
    store.loggedInUser.username = "";
    store.loggedInUser.role = "";
}

export default Logout;