import { Button, Container, Table } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import Logout from "../LoginRegister/Logout";
import { User } from "../Interfaces/User";
import { Reimbursement } from "../Interfaces/Reimbursement";
import { useEffect, useState } from "react";
import { store } from "../GlobalData/store";
import axios from "axios";

function ReimbursementTable ()
{
    const navigate = useNavigate();
    let showValue:Number = 2;
    const[reimbursements, setReimbursements] = useState<Reimbursement[]>([]);

    useEffect(() =>
    {
        if (store.loggedInUser.role == "manager")
        {
            getAllReimbursements(1);
        }
        else
        {
            getAllReimbursements(2);
        }
    }, []);
    
    const getAllReimbursements = async (num:Number) =>
    {
        if (num == 1 && store.loggedInUser.userId != 0)
        {
            try
            {
                const response = await axios.get("http://localhost:8080/reimbursements", {withCredentials:true});
    
                setReimbursements(response.data);
            }
            catch
            {
                alert("Something went wrong trying to fetch reimbursements");
            }
        }
        else if (num == 2 && store.loggedInUser.userId != 0)
        {
            try
            {
                const response = await axios.get("http://localhost:8080/reimbursements/" + store.loggedInUser.userId, {withCredentials:true});
    
                setReimbursements(response.data);
            }
            catch
            {
                alert("Something went wrong trying to fetch reimbursements");
            }
        }
        else if (num == 3 && store.loggedInUser.userId != 0)
        {
            try
            {
                const response = await axios.get("http://localhost:8080/reimbursements/pending", {withCredentials:true});
        
                setReimbursements(response.data);
            }
            catch
            {
                alert("Something went wrong trying to fetch reimbursements");
            }
        }
        else if (num == 4 && store.loggedInUser.userId != 0)
        {
            try
            {
                const response = await axios.get("http://localhost:8080/reimbursements/pending/" + store.loggedInUser.userId, {withCredentials:true});
            
                setReimbursements(response.data);
            }
            catch
            {
                alert("Something went wrong trying to fetch reimbursements");
            }
        }
        else
        {
            alert("User must be logged in to access this page!");
            navigate("/");
        }
    }

    const updateReimbursement = async (rID:Number, num:Number) => 
    {
        if (num == 1)
        {
            try
            {
                const response = await axios.patch("http://localhost:8080/reimbursements/" + rID + "/approved", {}, {withCredentials:true});

                alert("Approval Successful!")

                getAllReimbursements(showValue);
            }
            catch
            {
                alert("Something went wrong updating Reimbursement!")
            }
        }
        else
        {
            try
            {
                const response = await axios.patch("http://localhost:8080/reimbursements/" + rID + "/denied", {}, {withCredentials:true});

                alert("Denial Successful!")

                getAllReimbursements(showValue);
            }
            catch
            {
                alert("Something went wrong updating Reimbursement!")
            }
        }
    }

    // const displayUserId = (r:Reimbursement) => 
    // {
    //     const user:User = r.user;
    //     return (
    //         <td>{user.userId}</td>
    //     )
    // }
    const displayReimbursements = () =>
    {
        if (store.loggedInUser.role == "manager")
        {
            return(
                <Table className="table-dark table-hover table-striped w-50">
                    <thead>
                        <tr>
                            <th>Reimbursement ID</th>
                            <th>Description</th>
                            <th>Amount</th>
                            <th>Status</th>
                            <th>User ID</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                    reimbursements.map((reimbursement:Reimbursement) =>
                        (
                            <tr key={reimbursement.reimbursementID}>
                                <td>{reimbursement.reimbursementID}</td>
                                <td>{reimbursement.description}</td>
                                <td>${reimbursement.amount}</td>
                                <td>{reimbursement.status}</td>
                                <td>{reimbursement.userId}</td>
                                <td>
                                    <Button variant="outline-success" onClick={() => updateReimbursement(reimbursement.reimbursementID, 1)}>Approve</Button>
                                    <Button variant="outline-danger" onClick={() => updateReimbursement(reimbursement.reimbursementID,2)}>Deny</Button>
                                </td>
                            </tr>
                        ))
                    }
                    </tbody>
                </Table>
            );
        }
        else
        {
            return(
                <Table className="table-dark table-hover table-striped w-50">
                    <thead>
                        <tr>
                            <th>Reimbursement ID</th>
                            <th>Description</th>
                            <th>Amount</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                    reimbursements.map((reimbursement:Reimbursement) =>
                        (
                            <tr key={reimbursement.reimbursementID}>
                                <td>{reimbursement.reimbursementID}</td>
                                <td>{reimbursement.description}</td>
                                <td>${reimbursement.amount}</td>
                                <td>{reimbursement.status}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </Table>
            );
        }
    }

    const displayButtons = () =>
    {
        if (store.loggedInUser.role == "manager")
        {
            return(
                <div>
                    <Button className="btn-dark" onClick={() => {Logout(); navigate("/");}}>Logout</Button>
                    <Button className="btn-dark" onClick={() => {getAllReimbursements(1), showValue = 1}}>Show All</Button>
                    <Button className="btn-dark" onClick={() => {getAllReimbursements(2), showValue = 2}}>Show All Your Reimbursements</Button>
                    <Button className="btn-dark" onClick={() => {getAllReimbursements(3), showValue = 3}}>Show All Pending</Button>
                    <Button className="btn-dark" onClick={() => {getAllReimbursements(4), showValue = 4}}>Show All Your Pending</Button>
                    <Button className="btn-dark" onClick={() => navigate("/reimbursements/new")}>Create New Reimbursment</Button>
                </div>
            )
        }
        else
        {
            return(
                <div>
                    <Button className="btn-dark" onClick={() => {Logout(); navigate("/");}}>Logout</Button>
                    <Button className="btn-dark" onClick={() => getAllReimbursements(2)}>Show All</Button>
                    <Button className="btn-dark" onClick={() => getAllReimbursements(4)}>Show All Pending</Button>
                    <Button className="btn-dark" onClick={() => navigate("/reimbursements/new")}>Create New Reimbursment</Button>
                </div>
            )
        }
    }

    return(
        <Container className="d-flex flex-column align-items-center mt-3">
            {displayButtons()}

            <h3>Reimbursements:</h3>

            {displayReimbursements()}
            
        </Container>
    )
}

export default ReimbursementTable;