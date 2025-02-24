import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

function Register ()
{
    const navigate = useNavigate();

    const usernameRef = useRef<HTMLInputElement>(null);

    const[registerCreds, setRegisterCreds] = useState({
            username:"",
            password:"",
            firstname:"",
            lastname:""
        })

    const storeValues = (event:React.ChangeEvent<HTMLInputElement>) => {

        const name = event.target.name;
        const value = event.target.value;

        setRegisterCreds((registerCreds) => ({...registerCreds, [name]:value}));
    }

    useEffect(() => {
            if (usernameRef.current)
            {
                usernameRef.current.focus();
            }
        }, []);

    const register = async () => {
        
        try
        {
            const response = await axios.post("http://localhost:8080/auth/register", {
                username:registerCreds.username,
                password:registerCreds.password,
                firstName:registerCreds.firstname,
                lastName:registerCreds.lastname
            });

            if (response.status.valueOf() == 200)
            {
                alert("Registration Successful");
                navigate("/");
            }
            else
            {
                alert("Registration failed:/n" + response.data);               
            }
        }
        catch
        {
            alert("Registration failed");
        }


    }

    return(
        <Container>
            <div>
                <h1>New here? Create an Account for free!</h1>

                <div>
                    <Form.Control
                        type="text"
                        placeholder="username"
                        name="username"
                        ref={usernameRef}
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
                <div>
                    <Form.Control
                        type="text"
                        placeholder="firstname"
                        name="firstname"
                        onChange={storeValues}
                    />
                </div>
                <div>
                    <Form.Control
                        type="text"
                        placeholder="lastname"
                        name="lastname"
                        onChange={storeValues}
                    />
                </div>
                <div>
                    <Button className="btn-success m-1" onClick={register}>Create Account!</Button>
                    <Button className="btn-dark" onClick={() => navigate("/")}>Back to Login</Button>
                </div>
            </div>
        </Container>
    )
}

export default Register;