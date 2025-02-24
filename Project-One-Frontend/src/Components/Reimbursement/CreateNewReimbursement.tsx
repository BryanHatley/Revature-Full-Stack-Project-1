import { useRef, useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { store } from "../GlobalData/store";
import axios from "axios";

function CreateNewReimbursement ()
{
    const navigate = useNavigate();

    const descriptionRef = useRef<HTMLInputElement>(null);
    
    const[newReimbursement, setReimbursement] = useState({
            description:"",
            amount:0,
        })
    
    const storeValues = (event:React.ChangeEvent<HTMLInputElement>) => {
    
        const name = event.target.name;
        const value = event.target.value;
    
        setReimbursement((newReimbursement) => ({...newReimbursement, [name]:value}));
    }

    const createNewReimbursement = async () => 
    {
        try
        {
            const response = await axios.post("http://localhost:8080/reimbursements", {
                description:newReimbursement.description,
                amount:newReimbursement.amount,
                status:"pending",
                userId:store.loggedInUser.userId
            }, {withCredentials:true});

            if (response.status.valueOf() == 202)
            {
                alert("Creation Successful");
                navigate("/reimbursements");
            }
            else
            {
                alert("Creation failed:/n" + response.data);               
            }
        }
        catch
        {
            alert("Creation failed");
        }
    }

    return(
        <Container>
            <div>
                <h1>New here? Create an Account for free!</h1>

                <div>
                    <Form.Control
                        type="text"
                        placeholder="description"
                        name="description"
                        ref={descriptionRef}
                        onChange={storeValues}
                    />
                </div>
                <div>
                    <Form.Control
                        type="number"
                        placeholder="0"
                        name="amount"
                        onChange={storeValues}
                    />
                </div>
                <div>
                    <Button className="btn-success m-1" onClick={createNewReimbursement}>Create Reimbursement</Button>
                    <Button className="btn-dark" onClick={() => navigate("/reimbursements")}>Back to Reimbursements</Button>
                </div>
            </div>
        </Container>
    )
}

export default CreateNewReimbursement;