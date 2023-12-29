import React, { useState } from 'react'
import NavBar from '../component/NavBar'
import { Link, useNavigate } from 'react-router-dom'
import { verifyUser } from '../api-client/api-module';
import OtpDialog from '../component/OtpDialog';
import AlertDialog from '../component/AlertDialog';
import Loader from '../component/Loader';

export default function LoginPage() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [errors, setErrors] = useState({});
    const [alert, setAlert] = useState(false);
    const [showOtpBox, setShowOtpBox] = useState(false);
    const [response, setResp] = useState(false);
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const validateEmail = () => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    };

    const validatePassword = () => {
        return password.length >= 6;
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        e.preventDefault();
        const newErrors = {};

        if (!validateEmail()) {
            newErrors.email = 'Invalid email address';
        }

        if (!validatePassword()) {
            newErrors.password = 'Password must be at least 6 characters';
        }

        if (Object.keys(newErrors).length > 0) {
            setErrors(newErrors);
            return
        }
        else {
            setErrors({})
            setLoading(true)
            verifyUser(email, password).then((resp) => {
                if (resp) {
                    switch (resp.type) {
                        case 'admin':
                    navigate("/admin-dashboard");
                            
                            break;
                        case 'user':
                    navigate("/user-dashboard/"+resp.id);
                            
                            break;
                        case 'trainer':
                    navigate("/trainer-dashboard/"+resp.id);
                            
                            break;
                    
                        default:
                            break;
                    }
                }
                else {
                    setLoading(false);
                    setAlert(true);
                }
            })
        }
    };

    return (
        <>
            <NavBar />
            <div className="body-container">
                <div className="login-contaienr">
                    <div className="info-text">
                        <p>Login</p>
                    </div>
                    <div className="divider"></div>
                    <div className="login-box">
                        <form onSubmit={handleSubmit}>
                            <div className="inputbox m-3">
                                <input type="text" id='username' value={email} onChange={(e) => { setEmail(e.target.value) }} required />
                                <label>Username</label>
                                {errors.email && <span className="error">{errors.email}</span>}
                            </div>

                            <div className="inputbox m-3">
                                <input type="password" id='password' value={password} onChange={(e) => { setPassword(e.target.value) }} required />
                                <label>Password</label>
                            </div><input type="submit" value="Submit" />
                        </form>
                        <p>You don't Have Accout <Link to="/signup">Sign Up</Link></p>
                    </div>
                </div>
            </div>
            {loading && <Loader />}
            {alert && <AlertDialog title={'Invalid'} desc={'Invalid User Email Id or Password'} type={'error'} onClose={setAlert} />}
        </>
    )
}
