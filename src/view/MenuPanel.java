package view;

import com.sun.codemodel.internal.JOp;
import model.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;

/**
 * @author Stanley Hitchcock
 */
public class MenuPanel extends JPanel{

    private JComboBox<Integer> semiprimeField;
    private JFormattedTextField messageField;
    private JComboBox<Integer> qbitsField;
    private JButton okButton;
    private JButton quitButton;
    private boolean warned = false;
    private MainFrame frame;

    public void okPress() {
        try {
            int qbits = qbitsField.getItemAt(qbitsField.getSelectedIndex());
            int semiprime = semiprimeField.getItemAt(semiprimeField.getSelectedIndex());
            Long message = (Long)messageField.getValue();
//            System.out.println(message);
//            System.out.println(messageField.getValue());
            if (message >= semiprime) {
                JOptionPane.showMessageDialog(frame, "The message must be smaller than the semiprime", "" , JOptionPane.ERROR_MESSAGE);
            }
            else if (message <= 1) {
                JOptionPane.showMessageDialog(frame, "The message must be larger than 1", "" , JOptionPane.ERROR_MESSAGE);

            }
            else {
                int factor = State.extendedEuclid(message.intValue(),semiprime)[0];
                if (factor == 1) frame.showState(qbits, semiprime, message.intValue());
                else {
                    JOptionPane.showMessageDialog(frame, message + " and " + semiprime + "\n" +
                            "share a common factor: " + factor + "\n" +
                            "and can be factorised instantly: \n" +
                            semiprime + " = " + factor + " * " + semiprime / factor);
                }
            }
        }
        catch (NullPointerException e1) {
            JOptionPane.showMessageDialog(frame, "Please enter a value for the message!", "" , JOptionPane.ERROR_MESSAGE);
        }
    }

    public MenuPanel(final MainFrame mainFrame) {



        frame = mainFrame;

//        final Integer[] semiprimes = new Integer[]{15, 21, 33, 35, 39, 51, 55, 57, 65, 69, 77, 85, 87, 91,
//                93, 95, 111, 115, 119, 123, 129, 133, 141, 143, 145, 155, 159, 161, 177, 183, 185, 187,
//                201, 203, 205, 209, 213, 215, 217, 219, 221, 235, 237, 247, 249, 253, 259, 265, 267, 287,
//                291, 295, 299, 301, 303, 305, 309, 319, 321, 323, 327, 329, 335, 339, 341, 355, 365, 371,
//                377, 381, 391, 393, 395, 403, 407, 411, 413, 415, 417, 427, 437, 445, 447, 451, 453, 469,
//                471, 473, 481, 485, 489, 493, 497, 501, 505, 511, 515, 517, 519, 527, 533, 535, 537, 543,
//                545, 551, 553, 559, 565, 573, 579, 581, 583, 589, 591, 597, 611, 623, 629, 633, 635, 649,
//                655, 667, 669, 671, 679, 681, 685, 687, 689, 695, 697, 699, 703, 707, 713, 717, 721, 723,
//                731, 737, 745, 749, 753, 755, 763, 767, 771, 779, 781, 785, 789, 791, 793, 799, 803, 807,
//                813, 815, 817, 831, 835, 843, 849, 851, 865, 869, 871, 879, 889, 893, 895, 899, 901, 905,
//                913, 917, 921, 923, 933, 939, 943, 949, 951, 955, 959, 965, 973, 979, 985, 989, 993, 995,
//                1003, 1007};


        final Integer[] semiprimes = new Integer[]{15, 21, 33, 35, 39, 51, 55, 57, 65, 69,
                77, 85, 87, 91, 93, 95, 111, 115, 119, 123,
                129, 133, 141, 143, 145, 155, 159, 161, 177, 183,
                185, 187, 201, 203, 205, 209, 213, 215, 217, 219,
                221, 235, 237, 247, 249, 253, 259, 265, 267, 287,
                291, 295, 299, 301, 303, 305, 309, 319, 321, 323,
                327, 329, 335, 339, 341, 355, 365, 371, 377, 381,
                391, 393, 395, 403, 407, 411, 413, 415, 417, 427,
                437, 445, 447, 451, 453, 469, 471, 473, 481, 485,
                489, 493, 497, 501, 505, 511, 515, 517, 519, 527,
                533, 535, 537, 543, 545, 551, 553, 559, 565, 573,
                579, 581, 583, 589, 591, 597, 611, 623, 629, 633,
                635, 649, 655, 667, 669, 671, 679, 681, 685, 687,
                689, 695, 697, 699, 703, 707, 713, 717, 721, 723,
                731, 737, 745, 749, 753, 755, 763, 767, 771, 779,
                781, 785, 789, 791, 793, 799, 803, 807, 813, 815,
                817, 831, 835, 843, 849, 851, 865, 869, 871, 879,
                889, 893, 895, 899, 901, 905, 913, 917, 921, 923,
                933, 939, 943, 949, 951, 955, 959, 965, 973, 979,
                985, 989, 993, 995, 1003, 1007, 1011, 1027, 1037, 1041,
                1043, 1047, 1055, 1057, 1059, 1067, 1073, 1077, 1079, 1081,
                1099, 1101, 1111, 1115, 1119, 1121, 1133, 1135, 1137, 1139,
                1141, 1145, 1147, 1149, 1157, 1159, 1165, 1167, 1169, 1177,
                1189, 1191, 1195, 1199, 1203, 1205, 1207, 1211, 1219, 1227,
                1241, 1243, 1247, 1253, 1255, 1257, 1261, 1263, 1267, 1271,
                1273, 1285, 1293, 1299, 1313, 1315, 1317, 1329, 1333, 1337,
                1339, 1343, 1345, 1347, 1349, 1351, 1355, 1357, 1363, 1371,
                1379, 1383, 1385, 1387, 1389, 1391, 1393, 1397, 1401, 1403,
                1405, 1411, 1415, 1417, 1437, 1441, 1457, 1461, 1465, 1469,
                1473, 1477, 1497, 1501, 1507, 1509, 1513, 1517, 1527, 1529,
                1535, 1537, 1541, 1555, 1561, 1563, 1565, 1569, 1577, 1585,
                1589, 1591, 1603, 1623, 1631, 1633, 1639, 1641, 1643, 1649,
                1651, 1655, 1661, 1671, 1673, 1679, 1685, 1687, 1689, 1691,
                1703, 1707, 1711, 1713, 1717, 1727, 1731, 1735, 1739, 1745,
                1751, 1757, 1761, 1763, 1765, 1769, 1779, 1781, 1793, 1795,
                1797, 1799, 1803, 1807, 1817, 1819, 1821, 1829, 1835, 1837,
                1839, 1841, 1843, 1851, 1853, 1857, 1865, 1883, 1891, 1893,
                1895, 1897, 1903, 1909, 1915, 1919, 1921, 1923, 1927, 1929,
                1937, 1939, 1941, 1943, 1945, 1957, 1959, 1961, 1963, 1967,
                1969, 1977, 1981, 1983, 1985, 1991, 2005, 2019, 2021, 2031,
                2033, 2041, 2045, 2047, 2049, 2051, 2059, 2071, 2073, 2077,
                2095, 2101, 2103, 2105, 2117, 2119, 2123, 2127, 2147, 2149,
                2155, 2157, 2159, 2165, 2167, 2171, 2173, 2177, 2181, 2183,
                2189, 2191, 2195, 2199, 2201, 2215, 2217, 2219, 2227, 2229,
                2231, 2245, 2249, 2253, 2257, 2263, 2271, 2279, 2283, 2285,
                2291, 2305, 2307, 2315, 2317, 2319, 2321, 2323, 2327, 2329,
                2335, 2353, 2359, 2361, 2363, 2369, 2391, 2395, 2407, 2413,
                2419, 2427, 2429, 2433, 2435, 2443, 2449, 2453, 2455, 2461,
                2463, 2469, 2471, 2479, 2481, 2483, 2487, 2489, 2491, 2495,
                2497, 2501, 2507, 2509, 2513, 2515, 2517, 2519, 2533, 2537,
                2545, 2559, 2561, 2563, 2567, 2569, 2571, 2573, 2577, 2581,
                2587, 2589, 2599, 2603, 2605, 2611, 2615, 2623, 2627, 2629,
                2631, 2641, 2643, 2649, 2651, 2653, 2661, 2669, 2681, 2701,
                2705, 2721, 2723, 2733, 2735, 2743, 2747, 2757, 2759, 2761,
                2771, 2773, 2779, 2785, 2787, 2807, 2811, 2813, 2815, 2823,
                2827, 2831, 2839, 2841, 2845, 2855, 2859, 2863, 2867, 2869,
                2881, 2885, 2893, 2899, 2901, 2911, 2913, 2921, 2923, 2929,
                2931, 2933, 2935, 2941, 2947, 2949, 2951, 2959, 2965, 2973,
                2977, 2981, 2983, 2987, 2991, 2993, 2995, 3005, 3007, 3013,
                3017, 3027, 3029, 3031, 3035, 3039, 3043, 3047, 3053, 3057,
                3063, 3065, 3071, 3073, 3077, 3085, 3091, 3093, 3095, 3097,
                3099, 3101, 3103, 3107, 3113, 3117, 3127, 3131, 3133, 3139,
                3143, 3147, 3149, 3151, 3153, 3155, 3161, 3173, 3183, 3189,
                3193, 3197, 3199, 3205, 3207, 3215, 3223, 3227, 3233, 3235,
                3239, 3241, 3247, 3261, 3263, 3265, 3269, 3273, 3277, 3279,
                3281, 3287, 3291, 3293, 3295, 3305, 3309, 3317, 3327, 3337,
                3341, 3349, 3351, 3353, 3365, 3369, 3377, 3379, 3383, 3385,
                3387, 3397, 3401, 3403, 3409, 3415, 3419, 3421, 3427, 3431,
                3437, 3439, 3443, 3453, 3455, 3459, 3473, 3487, 3489, 3493,
                3497, 3503, 3505, 3513, 3521, 3523, 3543, 3545, 3551, 3561,
                3563, 3569, 3579, 3587, 3589, 3595, 3599, 3601, 3603, 3611,
                3629, 3635, 3639, 3641, 3647, 3649, 3651, 3653, 3661, 3665,
                3667, 3669, 3679, 3683, 3687, 3693, 3695, 3707, 3711, 3713,
                3715, 3737, 3743, 3747, 3749, 3755, 3763, 3777, 3781, 3785,
                3787, 3791, 3799, 3805, 3809, 3811, 3817, 3827, 3829, 3831,
                3837, 3839, 3841, 3845, 3849, 3859, 3865, 3867, 3869, 3873,
                3883, 3891, 3893, 3899, 3901, 3903, 3909, 3921, 3935, 3937,
                3941, 3949, 3953, 3957, 3959, 3961, 3963, 3973, 3977, 3979,
                3981, 3983, 3985, 3991, 3997, 4009, 4031, 4033, 4037, 4039,
                4043, 4045, 4055, 4061, 4063, 4069, 4083, 4087, 4097, 4101,
                4103, 4105, 4109, 4115, 4117, 4119, 4121, 4135, 4141, 4143,
                4145, 4151, 4163, 4169, 4171, 4181, 4183, 4187, 4189, 4193,
                4195, 4197, 4207, 4213, 4223, 4227, 4237, 4247, 4249, 4265,
                4267, 4269, 4279, 4281, 4285, 4287, 4291, 4295, 4299, 4303,
                4307, 4309, 4313, 4315, 4317, 4319, 4321, 4331, 4333, 4341,
                4343, 4351, 4353, 4359, 4367, 4369, 4377, 4379, 4381, 4385,
                4387, 4393, 4399, 4405, 4411, 4413, 4415, 4417, 4427, 4429,
                4435, 4439, 4443, 4449, 4453, 4461, 4467, 4469, 4471, 4479,
                4487, 4497, 4499, 4501, 4511, 4529, 4531, 4533, 4535, 4537,
                4541, 4553, 4555, 4559, 4569, 4571, 4573, 4577, 4579, 4589,
                4593, 4595, 4601, 4607, 4609, 4613, 4619, 4627, 4629, 4631,
                4633, 4645, 4647, 4659, 4661, 4667, 4677, 4681, 4685, 4687,
                4699, 4701, 4705, 4709, 4711, 4713, 4717, 4727, 4735, 4737,
                4739, 4741, 4747, 4749, 4757, 4763, 4765, 4769, 4771, 4777,
                4781, 4791, 4803, 4811, 4819, 4821, 4827, 4829, 4835, 4837,
                4839, 4841, 4843, 4847, 4849, 4853, 4855, 4857, 4859, 4863,
                4867, 4873, 4881, 4883, 4885, 4891, 4897, 4907, 4911, 4915,
                4927, 4939, 4955, 4963, 4971, 4979, 4981, 4985, 4989, 4997,
                5001, 5007, 5017, 5027, 5029, 5033, 5045, 5053, 5057, 5063,
                5065, 5069, 5071, 5079, 5089, 5091, 5093, 5095, 5097, 5105,
                5111, 5123, 5127, 5129, 5131, 5137, 5141, 5143, 5149, 5155,
                5161, 5163, 5165, 5169, 5173, 5177, 5183, 5191, 5195, 5199,
                5201, 5207, 5213, 5219, 5221, 5223, 5241, 5245, 5249, 5251,
                5255, 5257, 5259, 5263, 5267, 5269, 5277, 5287, 5293, 5299,
                5305, 5311, 5315, 5317, 5321, 5327, 5331, 5339, 5345, 5349,
                5353, 5357, 5359, 5361, 5363, 5367, 5371, 5377, 5383, 5389,
                5401, 5403, 5411, 5429, 5433, 5435, 5447, 5455, 5459, 5461,
                5465, 5469, 5473, 5485, 5489, 5493, 5497, 5509, 5513, 5515,
                5533, 5539, 5541, 5543, 5545, 5549, 5561, 5567, 5579, 5583,
                5585, 5587, 5597, 5599, 5601, 5603, 5609, 5611, 5613, 5615,
                5617, 5619, 5627, 5629, 5631, 5633, 5637, 5645, 5663, 5667,
                5671, 5677, 5699, 5703, 5707, 5713, 5721, 5723, 5729, 5731,
                5739, 5747, 5753, 5755, 5759, 5761, 5765, 5767, 5771, 5773,
                5777, 5789, 5793, 5799, 5803, 5809, 5815, 5833, 5837, 5847,
                5853, 5855, 5873, 5891, 5893, 5899, 5905, 5909, 5911, 5917,
                5919, 5921, 5933, 5935, 5937, 5941, 5947, 5951, 5959, 5961,
                5963, 5965, 5969, 5971, 5977, 5979, 5983, 5989, 5991, 5993,
                5997, 5999, 6001, 6005, 6009, 6013, 6017, 6019, 6023, 6031,
                6033, 6041, 6049, 6051, 6059, 6065, 6071, 6077, 6081, 6085,
                6087, 6103, 6107, 6109, 6115, 6117, 6119, 6127, 6139, 6145,
                6155, 6157, 6159, 6161, 6167, 6169, 6179, 6181, 6185, 6187,
                6189, 6191, 6193, 6207, 6209, 6227, 6233, 6239, 6243, 6245,
                6249, 6259, 6261, 6267, 6281, 6283, 6289, 6295, 6297, 6313,
                6319, 6331, 6333, 6339, 6341, 6347, 6349, 6371, 6377, 6383,
                6385, 6387, 6393, 6395, 6401, 6403, 6407, 6411, 6415, 6423,
                6429, 6431, 6433, 6437, 6439, 6443, 6445, 6455, 6457, 6459,
                6463, 6467, 6483, 6485, 6487, 6493, 6497, 6499, 6503, 6505,
                6509, 6511, 6515, 6523, 6527, 6533, 6535, 6537, 6539, 6541,
                6557, 6559, 6583, 6587, 6589, 6593, 6595, 6605, 6609, 6611,
                6613, 6617, 6621, 6623, 6629, 6631, 6635, 6639, 6641, 6649,
                6663, 6667, 6671, 6677, 6683, 6697, 6707, 6711, 6717, 6729,
                6731, 6739, 6743, 6749, 6751, 6753, 6757, 6767, 6769, 6773,
                6787, 6797, 6799, 6801, 6805, 6807, 6809, 6817, 6819, 6821,
                6835, 6839, 6843, 6847, 6861, 6865, 6879, 6881, 6887, 6891,
                6893, 6901, 6905, 6913, 6927, 6931, 6933, 6937, 6941, 6943,
                6953, 6973, 6979, 6989, 6995, 6999, 7003, 7009, 7017, 7023,
                7031, 7033, 7037, 7041, 7045, 7051, 7053, 7061, 7063, 7067,
                7071, 7073, 7081, 7087, 7091, 7093, 7097, 7099, 7111, 7113,
                7115, 7117, 7123, 7131, 7133, 7135, 7141, 7143, 7145, 7147,
                7149, 7153, 7157, 7165, 7167, 7169, 7171, 7179, 7181, 7183,
                7195, 7197, 7199, 7201, 7217, 7223, 7231, 7233, 7235, 7241,
                7249, 7251, 7255, 7261, 7265, 7269, 7271, 7273, 7277, 7279,
                7289, 7291, 7295, 7303, 7311, 7313, 7319, 7323, 7327, 7339,
                7341, 7343, 7355, 7357, 7361, 7363, 7367, 7373, 7377, 7379,
                7387, 7391, 7397, 7401, 7403, 7405, 7409, 7415, 7419, 7421,
                7423, 7427, 7431, 7435, 7439, 7441, 7445, 7447, 7453, 7463,
                7465, 7471, 7483, 7493, 7495, 7501, 7509, 7513, 7519, 7531,
                7543, 7555, 7563, 7571, 7593, 7597, 7601, 7609, 7613, 7615,
                7617, 7619, 7627, 7629, 7631, 7633, 7637, 7647, 7651, 7653,
                7655, 7661, 7663, 7671, 7679, 7697, 7709, 7711, 7715, 7721,
                7729, 7737, 7739, 7745, 7747, 7751, 7763, 7765, 7769, 7771,
                7773, 7779, 7781, 7783, 7787, 7795, 7799, 7801, 7807, 7811,
                7813, 7819, 7827, 7831, 7835, 7837, 7849, 7851, 7855, 7859,
                7861, 7863, 7871, 7891, 7895, 7897, 7899, 7903, 7909, 7913,
                7915, 7939, 7941, 7957, 7961, 7967, 7969, 7971, 7977, 7979,
                7981, 7985, 7989, 7991, 7997, 7999, 8003, 8005, 8013, 8021,
                8023, 8027, 8031, 8033, 8035, 8045, 8047, 8049, 8051, 8057,
                8061, 8063, 8065, 8067, 8071, 8077, 8079, 8083, 8095, 8097,
                8105, 8119, 8121, 8129, 8131, 8133, 8135, 8137, 8139, 8141,
                8143, 8149, 8153, 8157, 8159, 8173, 8185, 8187, 8189};
        final Integer[] qbits = new Integer[]{8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};



        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        semiprimeField = new JComboBox<Integer>(semiprimes);
        messageField = new JFormattedTextField(NumberFormat.getIntegerInstance());
//        messageField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
//        messageField.setOpaque(false);
        qbitsField = new JComboBox<Integer>(qbits);
        okButton = new JButton("OK");
        quitButton = new JButton("Quit");



        semiprimeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int power = 0;
                int semiprime = semiprimeField.getItemAt(semiprimeField.getSelectedIndex());
                if (semiprime > 1024) JOptionPane.showMessageDialog(frame,"<html><p>Choosing a large semiprime will require a large number of Qbits.</p>" +
                        "<p>It is recommended that the semiprime should stay under 1024.</p></html>","",JOptionPane.WARNING_MESSAGE);
                while (Math.pow(2, power) < semiprime) {
                    power++;
                }
                power *= 2;
                power -= 8;
                qbitsField.setSelectedIndex(power);
            }
        });



        qbitsField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int qbits = qbitsField.getItemAt(qbitsField.getSelectedIndex());
//                System.out.println(qbits);
                int semiprime = semiprimeField.getItemAt(semiprimeField.getSelectedIndex());


                if ((Math.pow(2,qbits/2) < semiprime || Math.pow(2,(qbits+1)/2-1) > semiprime) && !warned) {
                    Object[] options = {"Yes",
                            "No",};
                    int n = JOptionPane.showOptionDialog(null,"Choosing a lower number of Qbits can lead to inaccuracy,\n" +
                            "choosing a higher number than 20 will take a significant amount of time.\n" +
                            "Are you sure you want to do this?", "", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[1]);
                    if (n == 0) {
                        warned = true;
                    }
                    else {
                        semiprimeField.setSelectedIndex(semiprimeField.getSelectedIndex());
                    }
                }
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okPress();
            }
        });


        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object[] options = {"Yes",
                        "No",};
                int n = JOptionPane.showOptionDialog(frame,
                        "Are you sure you want to quit?",
                        "",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (n == 0) System.exit(0);
            }
        });

        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okPress();
            }
        });

        gc.insets = new Insets(5,5,5,5);



        gc.weightx = 0.5;
        gc.gridy = 0;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        Box semiprimeBox = new Box(BoxLayout.X_AXIS);
        JButton semiprimeInfo = new JButton("?");
        semiprimeInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"<html><p>A semiprime is the multiplication of two primes.</p>" +
                        "<p>This is the number we are looking to factorise.</p><p></p>" +
                        "<p>In the case of RSA this is n = p*q</p>" +
                        "</html>");
            }
        });
        semiprimeInfo.setSize(20,20);
        JTextArea semiprimeText = new JTextArea("Semiprime");
        semiprimeText.setBackground(null);
        semiprimeText.setFont(new Font(semiprimeText.getFont().getFontName(),Font.PLAIN,20));
        semiprimeBox.add(semiprimeText);
        semiprimeBox.add(semiprimeInfo);
        add(semiprimeBox, gc);

        gc.gridy = 1;

        Box messageBox = new Box(BoxLayout.X_AXIS);
        JButton messageInfo = new JButton("?");
        messageInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"<html><p>The message can be any number if we are looking to factorise,</p>" +
                        "<p>as long as it is smaller than the semiprime.</p><p></p>" +
                        "<p>If we are looking to decrypt a ciphertext we would enter it here.</p>" +
                        "</html>");
            }
        });
        JTextArea messageText = new JTextArea("Message");
        messageText.setBackground(null);
        messageText.setFont(new Font(messageText.getFont().getFontName(),Font.PLAIN,20));
        messageBox.add(messageText);
        messageBox.add(messageInfo);
        add(messageBox, gc);

        gc.gridy = 2;

        Box qbitBox = new Box(BoxLayout.X_AXIS);
        JButton qbitInfo = new JButton("?");
        qbitInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"<html><p>This is the amount of Qbits needed in the input register</p>" +
                        "<p>to effectively carry out the Quantum Fourier Transform.</p><p></p>" +
                        "<p>This will be worked out from the semiprime automatically</p>" +
                        "<p>but it can be changed if desired.</p><p></p>" +
                        "<p>If the number of Qbits exceeds 20,</p>" +
                        "<p>some parts of the program will execute very slowly.</p>" +
                        "</html>");
            }
        });
        JTextArea qbitText = new JTextArea("Qbits");
        qbitText.setBackground(null);
        qbitText.setFont(new Font(qbitText.getFont().getFontName(),Font.PLAIN,20));
        qbitBox.add(qbitText);
        qbitBox.add(qbitInfo);
        add(qbitBox, gc);

        gc.gridy = 0;
        gc.gridx = 1;

        add(semiprimeField,gc);

        gc.gridy = 1;
        add(messageField,gc);

        gc.gridy++;
        add(qbitsField,gc);

        gc.gridy++;
        gc.gridx = 0;
        add(okButton,gc);

        gc.gridx++;
        add(quitButton,gc);

//        messageField.requestFocus();
        semiprimeText.setEditable(false);
        qbitText.setEditable(false);
        messageText.setEditable(false);


    }


}
