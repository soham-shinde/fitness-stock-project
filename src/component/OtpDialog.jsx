import React,{useState} from 'react'
import { useNavigate } from 'react-router-dom';


export default function OtpDialog({resp,setValid,setActive}) {
    const navigate = useNavigate();
    const [otp, setOtp] = useState('');
    const [errors, setErrors] = useState({});


    const validateOtp = () => {
        return otp === resp.otp;
    };
    const redirectToDashboard = (userId) => {
        if(resp.otp === otp ){
            console.log();
            setValid(true);
            setActive(false);
        }
        else{
            setValid(false);
            setActive(false);
        }
      };
    
    const handleSubmit = (e) => {
        e.preventDefault();
        const newErrors = {};

        redirectToDashboard(`${resp.Id}~${resp.Token}`);
        
    }
    function handleOtp(params) {
        setOtp(params);
    }
    return (
        <div className="otp-dialog-overlay">
            <div className="otp-dialog-box">
                <form onSubmit={handleSubmit}>
                    <h1>Enter OTP</h1>
                    <p>Otp is sent to you register Email Address</p>
                    <input type="number" maxLength="6" value={otp} onChange={(e)=>handleOtp(e.target.value)} className="no-spin-buttons" />
                    {errors.otp && <span className="error">{errors.otp}</span>}
                    <button>Submit</button>
                </form>
            </div>
        </div>
    )
}
