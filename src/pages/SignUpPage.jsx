import React, { useEffect, useState } from 'react'
import NavBar from '../component/NavBar'
import { addCustomer, getMembershipPlanList, getOtp } from '../api-client/api-module';
import { useNavigate } from 'react-router-dom';
import MemCard from '../component/MemCard';
import OtpDialog from '../component/OtpDialog';
import { formatDate, validateContact, validateEmail, validateName, validateNumber, validatePassword } from '../api-client/validators';
import AlertDialog from '../component/AlertDialog';
import Loader from '../component/Loader';

export default function SignUpPage() {

    const [name, setName] = useState('');
    const [dob, setDob] = useState('');
    const [gender, setGender] = useState('');
    const [contactNo, setContactNo] = useState('');
    const [address, setAddress] = useState('');
    const [weight, setWeight] = useState('');
    const [height, setHeight] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [cpassword, setCPassword] = useState('');
    const [memCart, setMemCart] = useState();

    const [dataList, setDataList] = useState();
    const [uId, setUId] = useState(5);
    const [valid, setValid] = useState();
    const [active, setActive] = useState(false);
    const [response, setResponse] = useState()
    const [alert, setAlert] = useState(false);
    const [alertData, setAlertData] = useState({});
    const [errors, setErrors] = useState({});
    const [loading, setLoading] = useState(false);
    useEffect(() => {
        fetchData();
    }, []);

    useEffect(() => {
        aname();
        async function aname(params) {


            const formData = {
                "id": 0,
                "name": name,
                "dob": formatDate(dob),
                "gender": gender,
                "contactno": contactNo,
                "address": address,
                "weight": weight,
                "height": height,
                "username": username,
                "password": password
            }

            if (!active) {
                if (valid) {
                    const resp = await addCustomer(formData);
                    setUId(resp.id);
                    setMemCart(true);
                }
                else {
                    console.log("ndsifai");
                }
            }
        }

    }, [active])


    const fetchData = async () => {
        try {
            const list = await getMembershipPlanList();
            setDataList(list);

        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const newErrors = {};
        setLoading(true);
        if (!name || !password || !contactNo || !address | !username || !height || !dob || !weight) {
            newErrors.data = "Some Fields are empty"
        }
        if (password !== cpassword) {
            newErrors.data = 'Passwords do not match';
        }
        if (!validateEmail(username)) {
            newErrors.data = "Invalid username username should in email formate"
        }

        if (!validatePassword(password)) {
            newErrors.data = "Password should be minimum 6 digit"
        }
        if (!validateContact(contactNo)) {
            newErrors.data = "Invalid Number Enter 10 digit No."
        }

        if (Object.keys(newErrors).length > 0) {
            setErrors(newErrors);
            return;
        }
        getOtp(username).then((resp) => {
            if (resp) {
                setLoading(false);
                console.log(resp);
                setResponse(resp);
                setActive(true);
            }
            else{
                setAlertData({"title":"Error", "desc":"Error occur", "type":"error"})
                setAlert(true);
            }
        });


    };


    return (
        <>
            <NavBar />
            <div className="body-container">
                <div className="sign-contaienr">
                    <div className="info-text">
                        <p>Sign Up</p>
                    </div>
                    <div className="divider"></div>
                    <div className="login-box">
                        <form onSubmit={handleSubmit}>
                            <div className="inputbox-1">
                                <input type="text" id='name' value={name} onChange={(e) => {
                                    const newName = e.target.value;
                                    if (validateName(newName)) {
                                        setName(newName);
                                    }
                                }} required />
                                <label>Name</label>
                            </div>
                            <div className="inputbox-1">
                                <input type="text" id='username' value={username} onChange={(e) => setUsername(e.target.value)} required />
                                <label>Username</label>
                            </div>
                            <div className="input-grp">
                                <div className="inputbox-1">
                                    <input type="password" id='password' value={password} onChange={(e) => setPassword(e.target.value)} required />
                                    <label>Password</label>
                                </div>
                                <div className="inputbox-1">
                                    <input type="password" id='cpassword' value={cpassword} onChange={(e) => setCPassword(e.target.value)} required />
                                    <label>Confirm Password</label>
                                </div>
                            </div>
                            <div className="input-grp">
                                <div className="inputbox-1">
                                    <input type="date" id='dob' placeholder="" value={dob} onChange={(e) => setDob(e.target.value)} required />
                                    <label>Date of Birth</label>
                                </div>
                                <div className="inputbox-1">
                                    <input type="text" id='gender' value={gender} onChange={(e) => setGender(e.target.value)} required />
                                    <label>Gender</label>
                                </div>
                            </div>

                            <div className="inputbox-1">
                                <input type="tel" maxLength={10} id='contact' value={contactNo} onChange={(e) => {
                                    if (validateNumber(e.target.value)) {
                                        setContactNo(e.target.value)
                                    }
                                }
                                } required />
                                <label>Contact No</label>
                            </div>
                            <div className="inputbox-1">
                                <input type="text" id='address' value={address} onChange={(e) => setAddress(e.target.value)} required />
                                <label>Address</label>
                            </div>
                            <div className="input-grp">
                                <div className="inputbox-1">
                                    <input type="number" id='weight' value={weight} onChange={(e) => setWeight(e.target.value)} required />
                                    <label>Weight</label>
                                </div>
                                <div className="inputbox-1">
                                    <input type="number" id='height' value={height} onChange={(e) => setHeight(e.target.value)} required />
                                    <label>height</label>
                                </div>
                            </div>
                            {errors.data && <div className="error">{errors.data}</div>}

                            <input type="submit" value="Submit" />
                        </form>
                    </div>
                </div>
            </div>
            {memCart && <>
                <div className="modal">
                    <div className="modal-content entity-add-form">
                        <h2>Choice Membership Plan</h2>
                        <div className="membership-card-list">
                            {dataList && dataList.map(item => {
                                console.log(item);
                                return (
                                    <MemCard key={item.id} data={item} uid={uId} />
                                )
                            }
                            )}

                        </div>
                    </div>
                </div>
            </>}
            {active && <OtpDialog resp={response} setValid={setValid} setActive={setActive} />}
            {alert && <AlertDialog title={alertData.title} desc={alertData.desc} type={alertData.type} />}
            {loading && <Loader />}

        </>
    )
}
