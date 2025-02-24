import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { store } from "../GlobalData/store";

function Login() {

    const navigate = useNavigate();

    const usernameRef = useRef<HTMLInputElement>(null);

    useEffect(() => {
        if (usernameRef.current)
        {
            usernameRef.current.focus();
        }
    }, []);

    const[loginCreds, setLoginCreds] = useState({
        username:"",
        password:""
    })

    const storeValues = (event:React.ChangeEvent<HTMLInputElement>) => {

        const name = event.target.name;
        const value = event.target.value;

        setLoginCreds((loginCreds) => ({...loginCreds, [name]:value}));
    }

    const login = async () => {

        try
        {
            const response = await axios.post("http://localhost:8080/auth/login", loginCreds, {withCredentials:true});

            store.loggedInUser = response.data;

            alert(store.loggedInUser.username + " has logged in! Welcome.");

            if(store.loggedInUser.role === "manager"){
                navigate("/users");
            }
            else
            {
                navigate("/reimbursements");
            }
        }
        catch
        {
            alert("Login unsuccessful!");
        }
    }

    return(
        <Container>
            <h1>Welcome</h1>
                <h3>Please Log In:</h3>
                
                <div>
                    <Form.Control
                        type="text"
                        placeholder="username"
                        name="username"
                        ref={usernameRef} // attach our usernameRef here!
                        // This is how our useRef knows what to focus.
                        onChange={storeValues}
                    />
                </div>

                <div>
                    <Form.Control
                        type="password"
                        placeholder="password"
                        name="password"
                        onChange={storeValues}
                    />
                </div>

                <Button className="btn-success m-1" onClick={login}>Login</Button>
                <Button className="btn-dark" onClick={()=>navigate("/register")}>Register</Button>
        </Container>
    )

}

export default Login;